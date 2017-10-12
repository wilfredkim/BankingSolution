package Webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("/download")
public class BankStatementDownload {
    private static final String FILE_PATH = "C:\\Users\\Administrator\\Documents\\Bankstatement.txt";
    @GET
    @Path("/statement")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getFile() {
        File file = new File(FILE_PATH);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition","attachment; filename=\"BankStatement.txt\"");
        return response.build();

    }
}
