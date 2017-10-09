package drives.box;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class getacesstoken {
	/**
     * POST 요청
     * @param url       요청할 url
     * @param params    파라메터
     * @param encoding  파라메터 Encoding
     * @return 서버 응답결과 문자열
     */
	
	
    public String post(String url, Map params, String encoding){
        HttpClient client = new DefaultHttpClient();
         
        try{
            HttpPost post = new HttpPost(url);
            System.out.println("POST : " + post.getURI());
             
            List<NameValuePair> paramList = convertParam(params);
            post.setEntity(new UrlEncodedFormEntity(paramList, encoding));
             
            ResponseHandler<String> rh = new BasicResponseHandler();
 
            return client.execute(post, rh);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            client.getConnectionManager().shutdown();
        }
         
        return "error";
    }
     
    public String post(String url, Map params){
        return post(url, params, "UTF-8");
    }
 
     
     
   
     
     
     
    private List<NameValuePair> convertParam(Map params){
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        Iterator<String> keys = params.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            paramList.add(new BasicNameValuePair(key, params.get(key).toString()));
        }
         
        return paramList;
    }
     
   
     
 
    // Test
    public static void main(String[] args) throws ParseException {
        getacesstoken p = new getacesstoken();
        
        Map params = new HashMap();
        params.put("grant_type", "authorization_code");
        params.put("code","Ac9K7QnjOedDrv4ohaHXkU3h7fhohAnT" );
        params.put("client_id", "xgz1419a7mgtkklko63fki4tim8kdfug");
        params.put("client_secret","jX5a2NSLyRqVJcb1Cbicc5fW87mxDTLV");
        String response = p.post("https://api.box.com/oauth2/token", params);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse( response );
        JSONObject jsonObj = (JSONObject) obj;
        System.out.println("access_token : "+(String) jsonObj.get("access_token"));
        System.out.println("refresh_token : "+(String) jsonObj.get("refresh_token"));
    }





}
