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
import app.dao.ItemDAO2;

/**
 * Servlet implementation class ItemServlet2
 */
@WebServlet("/ItemServlet2")
public class ItemServlet2 extends HttpServlet {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードを設定
		request.setCharacterEncoding("utf-8");
		// リクエストパラメータのactionキーを取得
		String action = request.getParameter("action");
		try {
			// ItemDAO2を取得
			ItemDAO2 dao = new ItemDAO2();
			// actionキーによる処理の分岐
			if (action == null || action.isEmpty()) {
				// actionキーがnullまたは空文字列の場合は全商品を検索
				List<ItemBean> list = dao.findAll();
				// 結果をリクエストスコープに登録
				request.setAttribute("items", list);
				this.gotoPage(request, response, "/showItem2.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");	// TODO：DAOExceptionのメッセージに変更する（補充題）
			this.gotoPage(request, response, "/errInternal.jsp");
		}
	}

	/**
	 * 指定したURLに遷移する。
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param nextPage 遷移先URL
	 * @throws ServletException
	 * @throws IOException
	 */
	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String nextPage) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}