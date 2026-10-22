package inventory_vtiger_assets;

import org.testng.annotations.Test;

import BaseClass_Utility.BaseClass;
import Genric_Utility.DataProviderUtility;
import Genric_Utility.ScreenshotUtility;
import Object_repo.AssetsPage;
import Object_repo.HomePage;

public class assetsTest extends BaseClass{

	//verifying Critical feature
	@Test (priority = 1,groups = "Smoke")
	public void TC01() throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getAssetslink();
	}
	
	//Verify that an asset can be searched using Asset No.
	@Test (priority = 3,groups = "FT")
	public void TC02() throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getAssetslink();
		AssetsPage a = new AssetsPage(d);
		a.getsearchfor();
		a.getsearchIn();
		a.getsearchnow();
		a.verifybysearchIn();
	}
	
	//Verify that asset records are accessible through the More Information related-record sections.
	@Test (priority = 4,groups = "IT")
	public void TC03() throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getAssetslink();
		AssetsPage a = new AssetsPage(d);
		a.getsubject();
		a.getmoreinfo();
		a.getshowtickets();
		a.getverytickets();
		ScreenshotUtility.takeScreenshot(d, "Tickets");
		a.getshowdocuments();
		a.verifydocList();
		ScreenshotUtility.takeScreenshot(d, "Documents List");
	}
	
	//Verify that the complete asset creation workflow prevents saving an asset with an invalid Date Sold value.
	@Test (priority = 2,groups = "ST" , dataProvider = "assetData" , dataProviderClass = DataProviderUtility.class)
	public void TC04(String serial, String dateSold , String AssetName , String dateInService) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getAssetslink();
		AssetsPage a = new AssetsPage(d);
		a.getcreateassets();
		a.getserialNo(serial);
		a.getdateSold(dateSold);
		a.getdataservice(dateInService);
		a.getcustomerName();
		a.getproductName();
		a.getAssetName(AssetName);
		a.getassignedId();
		a.getsave();
		a.alertmess();
		logout();
	}
	
	//Verify that asset search works correctly after an asset's name is updated.
	@Test (priority = 5,groups = "RT",dataProvider = "AssetNameData", dataProviderClass = DataProviderUtility.class)
	public void TC05(String AssetNewName) throws Exception {
		login();
		HomePage h = new HomePage(d);
		h.getMorelink();
		h.getAssetslink();
		AssetsPage a = new AssetsPage(d);
		a.getsubject();
		a.geteditbtn();
		a.getchangeAssetName(AssetNewName);
		a.getsave();
		a.getassetLink();
		a.verifyAssetNameUpdated(AssetNewName);
		logout();
	}
}
