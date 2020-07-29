package Kevin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class weather extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public weather() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String weatherInfo = helper.getWeather(request.getParameter("query"));
			String cityname = (String) request.getParameter("city");
			//System.out.println(cityname);
			if(weatherInfo==null)
				throw new Exception("Something went wrong.");
			//response.getWriter().append(request.getParameter("query")).append(weatherInfo);
			JSONObject j1 = (JSONObject)new JSONParser().parse(weatherInfo);
			double temp = (double) j1.get("temperature");
			String icon = (String) j1.get("icon");
			
			request.setAttribute("temperature", temp );
			request.setAttribute("city", cityname);
			request.setAttribute("icon", icon);
			request.setAttribute("description", (String)j1.get("description"));
			request.getRequestDispatcher("weather_display.jsp").forward(request, response);
		}catch(Exception e) {
			response.getWriter().append(e.getMessage());
		}
	}

}
