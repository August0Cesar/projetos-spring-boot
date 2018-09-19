package br.com.augusto.business;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PagePostData;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.augusto.model.UsuarioFacebook;

@Component
public class LoginFacebook {
	//https://docs.spring.io/spring-social-facebook/docs/current/apidocs/org/springframework/social/facebook/api/package-summary.html

	public void obterUsuarioFacebook(String code) throws MalformedURLException, IOException {

		String retorno = readURL(new URL(this.getAuthURL(code)));

		String accessToken = null;
		@SuppressWarnings("unused")
		Integer expires = null;
		String[] pairs = retorno.split("&");
		for (String pair : pairs) {
			String[] kv = pair.split("=");
			System.out.println("Tamanho vetor : "+ kv.length);
			System.out.println("Dados vetor : "+ kv[0]);
			try {
				JSONObject respotaFacebook = new JSONObject(kv[0]);
				accessToken = respotaFacebook.getString("access_token");
				/*if(respotaFacebook.isNull("expires_in")) {
					expires = respotaFacebook.getInt("expires_in");
				}*/
				
				
				/*if (kv[0].equals("access_token")) {
					accessToken = kv[1];
				}
				if (kv[0].equals("expires")) {
					expires = Integer.valueOf(kv[1]);
				}*/
			} catch (JSONException e) {
				e.printStackTrace();
			}
			 
		}

		JSONObject resp;
		//JSONObject respP;
		try {
			//respP = new JSONObject(readURL(new URL("https://graph.facebook.com/v3.0/1683403105304562?fields=fan_count,access_token,emails,engagement,general_info&access_token=EAAaGZC1azJbQBADZA5ziXnoADj9Yfyvm2OSPjp9m3MuAYzhQgtTIxjB0R7HJcFuWoFcDyntMzxAlaZBftPTTRHaMwFZCUCPXZCpZAWDvYHZAfJ2zDh23h9zrtjFGi8wZB8RRrZCfNnvfqdZAljH5otQ1shmF0REGY68uZAl8FRgwZAb9paciTvR4vepjspyCRP9SxVUZD")));
			//System.out.println(respP.toString());
			System.out.println("-----");
			resp = new JSONObject(readURL(new URL("https://graph.facebook.com/v3.0/me?access_token=" + accessToken)));

			
			Facebook facebook = new FacebookTemplate(accessToken);
			
			System.out.println("##################Informacoes da Pagina Master Informatica##########################");
			ObjectMapper objetoSerializador = new ObjectMapper();
			
			
			Page paginaMasterInf = facebook.pageOperations().getPage("1683403105304562");
			
			System.out.println(objetoSerializador.writeValueAsString(paginaMasterInf));
			
			System.out.println("Token da paginaMaster " + paginaMasterInf.getAccessToken());
			//System.out.println("Token da pagina " + facebook.pageOperations().getAccessToken("1683403105304562"));
			//System.out.println(objetoSerializador.writeValueAsString(facebook.pageOperations().getPage("1683403105304562").getEngagement()));
			//fazer uma postagem na página do Facebook
			PagePostData novoPost = new PagePostData("185692818816355");
			novoPost.message("Video Teste, Imagem teste face");
			novoPost.link("https://i1.wp.com/gamelogia.com.br/wp-content/uploads/2016/11/gamer.jpg", null, null, null, null);
			
			
			
			
			
			System.out.println("Postando...");
			String idPost = facebook.pageOperations().post(novoPost);
			System.out.println("Id da postagem   ***" + idPost + "***"); 
			
			System.out.println("############################################");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	

	private String readURL(URL url) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = url.openStream();
		int r;
		while ((r = is.read()) != -1) {
			baos.write(r);
		}
		return new String(baos.toByteArray());
	}

	public String getLoginRedirectURL() {
		return "https://graph.facebook.com/v3.0/oauth/authorize?client_id=" + DadosDaAppNoFacebook.CLIENT_ID
				+ "&display=page&redirect_uri=" + DadosDaAppNoFacebook.REDIRECT_URI + "&scope=email,public_profile,"
						+ "user_likes,"
						+ "publish_pages,pages_show_list,manage_pages,pages_manage_cta";
	}

	public String getAuthURL(String authCode) {
		return "https://graph.facebook.com/oauth/access_token?client_id=" + DadosDaAppNoFacebook.CLIENT_ID
				+ "&redirect_uri=" + DadosDaAppNoFacebook.REDIRECT_URI + "&client_secret="
				+ DadosDaAppNoFacebook.CLIENT_SECRET + "&code=" + authCode;
	}

}
