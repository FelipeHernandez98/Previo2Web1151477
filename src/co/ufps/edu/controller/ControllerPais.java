package co.ufps.edu.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.ufps.edu.dao.PaisDao;
import co.ufps.edu.dao.PaisDaoFactory;
import co.ufps.edu.modelo.Pais;

/**
 * Servlet implementation class ControllerPais
 */
@WebServlet("/ControllerPais")
public class ControllerPais extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PaisDao paisDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerPais() {
        super();
        // TODO Auto-generated constructor stub
    }
    
public void init() throws ServletException {
		
		String type = getServletContext().getInitParameter("type");
		this.paisDao = PaisDaoFactory.getPaisDao(type);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String action = request.getServletPath();
	try {
	switch(action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			insertarPais(request, response);
			break;
		case "/delete":
			eliminarPais(request, response);
			break;
		case "/edit":
			showEditForm(request, response);
			break;
		case "/update":
			actualizarPais(request, response);
			break;
		default:
			listPaises(request, response);
			break;
	
	}
	}catch(SQLException e) {
		throw new ServletException(e);
	}
	
	
}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("pais.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertarPais(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		
		Pais pais= new Pais (id, name);
		
		paisDao.insert(pais);
		
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String id = (request.getParameter("id"));
		
		Pais paisActual = paisDao.select(id);
		
		request.setAttribute("pais", paisActual);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("pais.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void actualizarPais(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
	
		
		Pais pais = new Pais (id,name);
		
		paisDao.update(pais);
		
		response.sendRedirect("list");
	}
	
	private void eliminarPais(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		
		String id = (request.getParameter("id"));
		
		
		paisDao.delete(id);
		
		response.sendRedirect("list");
	}
	
	private void listPaises(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, SQLException, IOException {
		
		List <Pais> listPaises = paisDao.selectAll();
		request.setAttribute("listPaises", listPaises);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("paislist.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
