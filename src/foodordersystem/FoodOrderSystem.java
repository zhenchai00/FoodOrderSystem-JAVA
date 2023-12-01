package foodordersystem;

import foodordersystem.Model.DataIO;
import foodordersystem.Model.User;
import foodordersystem.Page.CustomerOrderPage;
import foodordersystem.Page.LoginPage;
import foodordersystem.Page.NewOrderPage;
import foodordersystem.Page.OrderCartPage;
import foodordersystem.Page.OrderPage;
import foodordersystem.Page.RegisterUserPage;
import foodordersystem.Page.VendorMenuPage;

import foodordersystem.Page.RevenueReportPage;
public class FoodOrderSystem {
    public static User currentUser;
	public static LoginPage loginPage;
	public static RegisterUserPage registerUserPage;

	// Not used because converted to singleton pattern
	// public static AdminDashboardPage adminDashboardPage;
	// public static CustomerDashboardPage	customerDashboardPage;
	// public static VendorDashboardPage vendorDashboardPage;
	// public static RunnerDashboardPage runnerDashboardPage;

	public static VendorMenuPage vendorMenuPage;
	public static OrderPage orderPage;
	public static CustomerOrderPage customerOrderPage;
	public static NewOrderPage newOrderPage;
	public static OrderCartPage orderCartPage;
    public static RevenueReportPage RevenueReportPage;

	public static void main(String[] args) {
		DataIO.readData();

		loginPage = new LoginPage();
		registerUserPage = new RegisterUserPage();

		vendorMenuPage = new VendorMenuPage();
		customerOrderPage = new CustomerOrderPage();
		newOrderPage = new NewOrderPage();
		orderCartPage = new OrderCartPage();
		
	}
}
