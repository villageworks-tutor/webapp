package app.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.bean.ItemBean;
import app.dao.DAOException;
import app.dao.ItemDAO;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの解析はなし

		// モデルを使って全商品を取得
		try {
			ItemDAO dao = new ItemDAO();
			List<ItemBean> list = dao.findAll();
			// 結果をリクエストスコープに登録して画面遷移
			request.setAttribute("items", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/showItem.jsp");
			dispatcher.forward(request, response);
		} catch (DAOException e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/errInternal.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
