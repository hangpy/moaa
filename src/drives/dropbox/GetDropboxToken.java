package drives.dropbox;

import java.io.IOException;

import java.util.Locale;


import com.dropbox.core.v1.DbxAccountInfo;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.v1.DbxClientV1;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.json.JsonReader;

public class GetDropboxToken {

	
	
	DbxClientV1 dbxClient;

	public String authDropbox(String dropBoxAppKey, String dropBoxAppSecret, String dropbox_ac_code)
			throws IOException, DbxException {
		DbxAppInfo dbxAppInfo = new DbxAppInfo(dropBoxAppKey, dropBoxAppSecret);
		DbxRequestConfig dbxRequestConfig = new DbxRequestConfig("USER_ID/1.0", Locale.getDefault().toString());
		DbxWebAuthNoRedirect dbxWebAuthNoRedirect = new DbxWebAuthNoRedirect(dbxRequestConfig, dbxAppInfo);
		
		String dropboxAuthCode = dropbox_ac_code;
		
		
		DbxAuthFinish authFinish = dbxWebAuthNoRedirect.finish(dropboxAuthCode);
		

		//수정필요
		String authAccessToken = authFinish.getAccessToken();
		/*JsonReader<DbxAuthFinish> refreshToken = authFinish.Reader;*/
		
		/*System.out.println(refreshToken);
		System.out.println(authAccessToken);
		dbxClient = new DbxClientV1(dbxRequestConfig, authAccessToken);*/
		

		/*return dbxClient;*/
		return authAccessToken;
	}

	/* returns Dropbox size in GB */
	public long getDropboxSize() throws DbxException {
		long dropboxSize = 0;
		DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo();
		// in GB :)
		dropboxSize = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
		return dropboxSize;
	}


	public void createFolder(String folderName) throws DbxException {
		dbxClient.createFolder("/" + folderName);
	}


}
