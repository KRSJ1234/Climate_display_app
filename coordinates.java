package Kevin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


public class coordinates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public coordinates() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ansResult = "";
		try {
			//PrintWriter out1 = response.getWriter();
			//out1.println("This is coordinate servelet <br>");
			ansResult = helper.getCoordinates(request.getParameter("placename"));
			//PrintWriter out = response.getWriter();
			//out.println("ansResult = "+ ansResult+"<br>");
			if(ansResult==null)
				throw new Exception("Couldnt connect to server.Please check the connection.\n");
			else if(ansResult.isEmpty())
				throw new Exception("The city name could be wrong. Please try with the correct name or another city");
			JSONObject jo = (JSONObject)new JSONParser().parse(ansResult);
			double lat = (double) jo.get("latitude");
			double lon = (double) jo.get("longitude");
			request.setAttribute("latitude", lat);
			request.setAttribute("longitude", lon);
			request.setAttribute("city",request.getParameter("placename"));
			request.setAttribute("query", ansResult);
			request.getRequestDispatcher("./coordinates_display.jsp").forward(request, response);
		} catch (ParseException e) {
			response.getWriter().append("Something went wrong(Parse Exception)\n").append(e.getMessage());
		} catch (IOException e) {
			response.getWriter().append("Something went wrong(IOexception)\n").append(e.getMessage());
		} catch (Exception e) {
			response.getWriter().append("Something went wrong\n").append(e.getMessage());
		}
		
	}

}
