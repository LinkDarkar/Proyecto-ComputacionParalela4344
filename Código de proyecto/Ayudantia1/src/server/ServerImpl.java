package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Product;
import common.ServerInterface;
import common.User;

public class ServerImpl implements ServerInterface
{
	public ServerImpl() throws RemoteException
	{
		this.connectToBD();
		UnicastRemoteObject.exportObject(this, 0);
	}
	
	// crear arreglo para respaldo de bd
	// to save a copy of the database for easier and faster access
	private ArrayList<String> jsonListFromAPI = new ArrayList<>();
	private ArrayList<Double> exchangeList = new ArrayList<>();
	private ArrayList<User> databaseUsersCopy = new ArrayList<>();
	private ArrayList<Product> databaseProductsCopy = new ArrayList<>();

	public void connectToBD()
	{
		Connection connection = null;
		Statement queryProducts = null;
		Statement queryUsers = null;
		ResultSet productsSet = null;
		ResultSet userSet = null;
		// PreparedStatement has some security barriers so that the code that the user ends up sending
		// isn't immediately executed by the server
		// PreparedStatement test = null;
		
		try
		{
			String url = "jdbc:mysql://localhost:3306/proyectoici4344";
			String username = "root";
			String password_BD = "";			// surely nothing bad will happen right???	
			connection = DriverManager.getConnection(url, username, password_BD);
			
			//TODO Metodos con la BD
			// apparently we have to make the commands like this to do shit
			queryProducts = connection.createStatement();
			queryUsers = connection.createStatement();
			String sqlProducts = "SELECT * FROM `products`";
			String sqlUsers = "SELECT * FROM `users`";
			productsSet = queryProducts.executeQuery(sqlProducts);
			userSet = queryUsers.executeQuery(sqlUsers);

			//INSERT para agregar datos a la BD, PreparedStatement
			while (productsSet.next())
			{
				int productId = productsSet.getInt("productId");
				String productName = productsSet.getString("productName");
				String productDescription = productsSet.getString("productDescription");
				int productCurrency = productsSet.getInt("productCurrency");
				int productPrice = productsSet.getInt("productPrice");
				
				Product newProduct = new Product (productId, productName, productDescription, productCurrency, productPrice);
				databaseProductsCopy.add(newProduct);
			}
			while (userSet.next())
			{
				int userId = userSet.getInt("userId");
				String userName = userSet.getString("userName");
				String password = userSet.getString("password");
				int location = userSet.getInt("location");
				
				User newUser = new User (userId, userName, password, location);
				databaseUsersCopy.add(newUser);
			}
			connection.close();
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
			System.out.println("Could not connect to the database");
		}
	}
	
	@Override
	public ArrayList<User> getUsers() throws RemoteException
	{
		// TODO Auto-generated method stub
		return databaseUsersCopy;
	}

	@Override
	public ArrayList<String> GetDataFromApi()
	{
		try
		{
            URL apiUrlToUSD = new URL("https://cl.dolarapi.com/v1/cotizaciones/usd");
			URL apiUrlToEUR = new URL("https://cl.dolarapi.com/v1/cotizaciones/eur");
			URL apiUrlToBRL = new URL("https://cl.dolarapi.com/v1/cotizaciones/brl");
			URL apiUrlToARS = new URL("https://cl.dolarapi.com/v1/cotizaciones/ars");
			URL apiUrlToUYU = new URL("https://cl.dolarapi.com/v1/cotizaciones/uyu");

			this.jsonListFromAPI.add(GetJsonFromAPI(apiUrlToUSD));
			this.jsonListFromAPI.add(GetJsonFromAPI(apiUrlToEUR));
			this.jsonListFromAPI.add(GetJsonFromAPI(apiUrlToBRL));
			this.jsonListFromAPI.add(GetJsonFromAPI(apiUrlToARS));
			this.jsonListFromAPI.add(GetJsonFromAPI(apiUrlToUYU));
        }
		catch (Exception exception)
		{
            exception.printStackTrace();
        }
		//Como resultado tenemos un String output que contiene el JSON de la respuesta de la API
		try
		{
			for (String tempJson : this.jsonListFromAPI)
			{
				System.out.println("Json: " + tempJson);
				this.exchangeList.add(GetExchange(tempJson));
			}
		}
		catch (RemoteException remoteException)
		{
			remoteException.printStackTrace();
		}
		return this.jsonListFromAPI;
	}

	private String GetJsonFromAPI (URL apiUrl)
	{
		String json = null;
		try
		{
			HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null)
				{
					response.append(inputLine);
				}

				in.close();
				json = response.toString();
				System.out.println("Connection with API succesful! for: " + apiUrl);
			}
			else
			{
				System.out.println("Connection with API failed, response code: " + responseCode);
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		return json;
	}

	@Override
	public Double GetExchange(String json) throws RemoteException
	{
		ObjectMapper objectMapper = new ObjectMapper();
		
		try
		{
			JsonNode jsonNode = objectMapper.readTree(json);
			double valor = jsonNode.get("compra").asDouble();
			System.out.println("valor obtenido: " + valor);
			// String codigo = jsonNode.get("uf").get("codigo").asText(); 
			
			return valor;
		}
		catch (JsonMappingException jsonMappingException)
		{
			jsonMappingException.printStackTrace();
		}
		catch (JsonProcessingException jsonProcessingException)
		{
			jsonProcessingException.printStackTrace();
		}
		
		return null;
	}
}
