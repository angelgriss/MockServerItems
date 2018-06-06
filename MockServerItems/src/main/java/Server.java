import com.google.gson.Gson;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.Delay;
import org.mockserver.model.Header;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class Server {

    static MockServerClient mockServer = startClientAndServer(1500);

    public static void main(String[] args) {

        Gson gson=new Gson();
        //getItem_Validar_Status_200OK
        mockServer
                .when(request()
                        .withMethod("GET")
                        .withPath("/items/ML0001")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withDelay(new Delay(SECONDS, 1)));

        //getItem_Validar_Item_Inexistente
        StandarResponse sr=new StandarResponse(StatusResponse.ERROR);
        sr.setMessage("El Item no existe");

        mockServer
                .when(request()
                        .withMethod("GET")
                        .withPath("/items/dsdsdsdasda")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json;charset=utf-8"))
                                .withBody(gson.toJson(sr))
                                .withDelay(new Delay(SECONDS, 1)));

        //getAll_Validar_Status_200OK
        mockServer
                .when(request()
                        .withMethod("GET")
                        .withPath("/items/all")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withDelay(new Delay(SECONDS, 1)));

        //postItem_Validar_Request_mal_formado
        mockServer
                .when(request()
                        .withMethod("POST")
                        .withPath("/items/ML0001")
                        .withBody("asdf")
                )
                .respond(
                        response()
                                .withStatusCode(400)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withDelay(new Delay(SECONDS, 1)));

        //postItem_Validar_Status_200OK
        Item item=new Item();

        item.setTitle("Item prueba.");
        item.setAvailable_quantity("2");
        item.setCurrency_id("ARS");
        item.setPrice("80");
        item.setCondition("new");
        item.setDescription("Producto desc");
        item.setListing_type_id("bronze");
        item.setBuying_mode("buy_it_now");
        item.setCategory_id("MLA5529");
        item.setVideo_id("youtube_ID");
        item.setWarranty("desc");

        Pictures[] pictures=new Pictures[1];
        Pictures pic=new Pictures();
        pic.setSource("img_url");
        pictures[0]=pic;
        item.setPictures(pictures);

        String reqbody= new Gson().toJson(item);

        mockServer
                .when(request()
                        .withMethod("POST")
                        .withPath("/items/ML000M1")
                        .withBody(reqbody)
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withDelay(new Delay(SECONDS, 1)));

        //postItem_Validar_Campos_Nulos
        Item item1=new Item();

        item1.setAvailable_quantity("2");
        item1.setCurrency_id("ARS");
        item1.setPrice("80");
        item1.setCondition("new");
        item1.setDescription("Producto desc");
        item1.setListing_type_id("bronze");
        item1.setBuying_mode("buy_it_now");
        item1.setCategory_id("MLA5529");
        item1.setVideo_id("youtube_ID");
        item1.setWarranty("desc");

        Pictures[] pictures1=new Pictures[1];
        Pictures pic1=new Pictures();
        pic1.setSource("img_url");
        pictures1[0]=pic1;
        item1.setPictures(pictures1);

        String reqbody1= new Gson().toJson(item1);

        StandarResponse sr1=new StandarResponse(StatusResponse.ERROR);
        sr1.setMessage("No se han completado todos los campos requeridos");


        mockServer
                .when(request()
                        .withMethod("POST")
                        .withPath("/items/ML000M1")
                        .withBody(reqbody1)
                )
                .respond(
                        response()
                                .withBody(gson.toJson(sr1))
                                .withDelay(new Delay(SECONDS, 1)));

        //putItem_Validar_Campos_Nulos
        Item item2=new Item();

        item2.setAvailable_quantity("2");
        item2.setCondition("new");
        item2.setDescription("Producto desc");
        item2.setListing_type_id("bronze");
        item2.setBuying_mode("buy_it_now");
        item2.setCategory_id("MLA5529");
        item2.setVideo_id("youtube_ID");
        item2.setWarranty("desc");

        Pictures[] pictures2=new Pictures[1];
        Pictures pic2=new Pictures();
        pic2.setSource("img_url");
        pictures2[0]=pic2;
        item2.setPictures(pictures2);

        String reqbody2= new Gson().toJson(item2);

        mockServer
                .when(request()
                        .withMethod("PUT")
                        .withPath("/items/ML000M1")
                        .withBody(reqbody2)
                )
                .respond(
                        response()
                                .withBody(gson.toJson(sr1))
                                .withDelay(new Delay(SECONDS, 1)));

        //putItem_Validar_Request_mal_formado
        mockServer
                .when(request()
                        .withMethod("PUT")
                        .withPath("/items/ML000M1")
                        .withBody("asdf")
                )
                .respond(
                        response()
                                .withStatusCode(400)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withDelay(new Delay(SECONDS, 1)));

        //putItem_Validar_Status_200OK
        Item item3=new Item();

        item3.setTitle("Item prueba update");
        item3.setAvailable_quantity("2");
        item3.setCurrency_id("ARS");
        item3.setPrice("80");
        item3.setCondition("new");
        item3.setDescription("Producto desc");
        item3.setListing_type_id("bronze");
        item3.setBuying_mode("buy_it_now");
        item3.setCategory_id("MLA5529");
        item3.setVideo_id("youtube_ID");
        item3.setWarranty("desc");

        Pictures[] pictures3=new Pictures[1];
        Pictures pic3=new Pictures();
        pic3.setSource("img_url");
        pictures3[0]=pic3;
        item3.setPictures(pictures3);

        String reqbody3= new Gson().toJson(item3);


        mockServer
                .when(request()
                        .withMethod("PUT")
                        .withPath("/items/ML000M1")
                        .withBody(reqbody3)
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withDelay(new Delay(SECONDS, 1)));

        //putItem_Validar_Item_Inexistente
        StandarResponse sr2=new StandarResponse(StatusResponse.ERROR);
        sr2.setMessage("document missing");


        mockServer
                .when(request()
                        .withMethod("PUT")
                        .withPath("/items/asdasada")
                )
                .respond(
                        response()
                                .withBody(gson.toJson(sr2))
                                .withDelay(new Delay(SECONDS, 1)));

        //deleteItem_Validar_Item_Inexistente
        StandarResponse sr3=new StandarResponse(StatusResponse.ERROR);
        sr3.setMessage("El Item no existe");


        mockServer
                .when(request()
                        .withMethod("DELETE")
                        .withPath("/items/dsdsdsdasda")
                )
                .respond(
                        response()
                                .withBody(gson.toJson(sr3))
                                .withDelay(new Delay(SECONDS, 1)));

        //deleteItem_Validar_Status_200OK
        mockServer
                .when(request()
                        .withMethod("DELETE")
                        .withPath("/items/ML000M1")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"))
                                .withDelay(new Delay(SECONDS, 1)));

    }
}
