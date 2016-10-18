package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import json.Response;

@WebServlet("/validateservlet")
public class ValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ValidateServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		Response responseJSON = new Response();

		List<String> usedLogins = Arrays.asList("Ziutek", "Leon", "Marcin", "Tomasz", "MrocznyZniwiarz", "Gorący96");
		if (login == null || login.length() == 0) {
			responseJSON.setValid(false);
			responseJSON.setLogin("Login nie może byc pusty");
		} else if (usedLogins.contains(login)){
			responseJSON.setValid(false);
			responseJSON.setLogin("Login jest już zajęty");
		}
		
		if (name == null || name.length() == 0) {
			responseJSON.setValid(false);
			responseJSON.setName("Imie nie może byc puste");
		}

		if (surname == null || surname.length() == 0) {
			responseJSON.setValid(false);
			responseJSON.setSurname("Naziwsko nie może byc puste");
		}
		
		if (!validateEmail(email)){
			responseJSON.setValid(false);
			responseJSON.setEmail("Adres email jest niepoprawny");
		}
		
	    String json = new Gson().toJson(responseJSON);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	}

	public static boolean validateEmail(String emailStr) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
}
