package common;

import java.io.Serializable;

public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	// ?????
	
	private int userId;
	private String userName;
	private String password;
	private int location;
	
	public User (int userId, String userName, String password, int location)
	{
		this.setUserId(userId);
		this.setUserName(userName);
		this.setPassword(password);		
		this.setLocation(location);	
	}
	
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public int getLocation()
	{
		return location;
	}
	public void setLocation(int location)
	{
		this.location = location;
	}
}
