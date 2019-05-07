package practica;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;


@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	System.out.println("java bean desde el servelt");
    	Usuario persona=new Usuario("jose","pererz");
    	RequestDispatcher rd=request.getRequestDispatcher("/jbean.jsp");
    	request.setAttribute("persona",persona);
    	rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
