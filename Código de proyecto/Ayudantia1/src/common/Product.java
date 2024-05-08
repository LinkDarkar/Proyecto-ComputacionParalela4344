package common;

import java.io.Serializable;

public class Product implements Serializable
{
    private int productId;
	private String productName;
	private String productDescription;
	private int productCurrency;
    private int productPrice;
	
	public Product (int productId, String productName, String productDescription, int productCurrency, int productPrice)
	{
		this.setProductId(productId);
		this.setProductName(productName);
		this.setProductDescription(productDescription);		
		this.setProductCurrency(productCurrency);	
        this.setProductPrice(productPrice);
	}
	
	public int getProductId()
	{
		return productId;
	}
	public void setProductId(int productId)
	{
		this.productId = productId;
	}
	public String getProductName()
	{
		return productName;
	}
	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	public String getProductDescription()
	{
		return productDescription;
	}
	public void setProductDescription(String productDescription)
	{
		this.productDescription = productDescription;
	}
	public int getProductCurrency()
	{
		return productCurrency;
	}
	public void setProductCurrency(int productCurrency)
	{
		this.productCurrency = productCurrency;
	}
    public int getProductPrice()
    {
        return productPrice;
    }
    public void setProductPrice(int productPrice)
    {
        this.productPrice = productPrice;
    }
}
