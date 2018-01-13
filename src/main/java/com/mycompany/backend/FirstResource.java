/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Khalil
 */
@Path("first/{varX}")
public class FirstResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FirstResource
     */
    public FirstResource() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.backend.FirstResource
     * @return an instance of java.lang.String
     */
    @GET
    public JsonArray dukes(@PathParam("varX") String varX){
        
        if (varX.equals("all")){
        JsonArrayBuilder  builder = Json.createArrayBuilder();
        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://192.168.6.16:8080/WebCrawler/webresources/generic");
        String h = target.request(MediaType.APPLICATION_JSON).get(String.class);
        try {
            JSONArray jsonArr = new JSONArray(h);
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject jsonObj = jsonArr.getJSONObject(i);
                     builder.add(createElement(jsonObj.getString("category"),jsonObj.getString("region"),jsonObj.getString("modelYear"),jsonObj.getString("mileage"), jsonObj.getString("brand"), jsonObj.getString("energy"),jsonObj.getString("model"),jsonObj.getString("power")));
            }
        } catch (JSONException ex) {
                    System.out.println("erreeeeeeeur !!! !");
        }

        System.out.println(varX);
         return   builder.build()  ;
        }
        else {
                JsonArrayBuilder  builder = Json.createArrayBuilder();
        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://192.168.6.16:8080/WebCrawler/webresources/generic");
        String h = target.request(MediaType.APPLICATION_JSON).get(String.class);
        try {
            JSONArray jsonArr = new JSONArray(h);
            for (int i = 0; i < jsonArr.length(); i++) {
                    JSONObject jsonObj = jsonArr.getJSONObject(i);

                if (varX.contains(jsonObj.getString("category"))||varX.contains(jsonObj.getString("region"))||varX.contains(jsonObj.getString("modelYear"))||varX.contains(jsonObj.getString("mileage"))||varX.contains(jsonObj.getString("brand"))||varX.contains(jsonObj.getString("energy"))||varX.contains(jsonObj.getString("model"))||varX.contains(jsonObj.getString("power"))){
                     builder.add(createElement(jsonObj.getString("category"),jsonObj.getString("region"),jsonObj.getString("modelYear"),jsonObj.getString("mileage"), jsonObj.getString("brand"), jsonObj.getString("energy"),jsonObj.getString("model"),jsonObj.getString("power")));
                }
                }
        } catch (JSONException ex) {
                    System.out.println("erreeeeeeeur !!! !");
        }

        System.out.println(varX);
         return   builder.build()  ;
        
        }

    }
       
    public JsonObject createElement (String category,String region,String modelYear  , String mileage ,String brand ,String energy,String model,String power){
         return Json.createObjectBuilder().add("category", category)
              .add("region", region)
              .add("modelYear", modelYear)
              .add("mileage", mileage)
              .add("brand", brand)
              .add("energy", energy)
              .add("model", model)
              .add("power", power)
              .build();
    } 

    /**
     * PUT method for updating or creating an instance of FirstResource
     * @param content representation for the resource
     */

 
}

