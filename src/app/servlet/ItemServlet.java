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
 * Servlet implementation class ItemServlet2
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {

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
			ItemDAO dao = new ItemDAO();
			// actionキーによる処理の分岐
			if (action == null || action.isEmpty()) {
				// actionキーがnullまたは空文字列の場合は全商品を検索
				List<ItemBean> list = dao.findAll();
				// 結果をリクエストスコープに登録して画面遷移
				request.setAttribute("items", list);
				this.gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("sort")) {
				// keyキーを取得
				String key = request.getParameter("key");
				// keyキーによる引数の霧前
				boolean isAscending = true;
				if (key.equals("price_desc")) {
					isAscending = false;
				}
				// 並べ替えた商品リストを取得
				List<ItemBean> list = dao.sortPrice(isAscending);
				// 結果をリクエストスコープに登録して画面遷移
				request.setAttribute("items", list);
				this.gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("search")) {
				// priceキーを取得
				int price = Integer.parseInt(request.getParameter("price"));	// TODO：数字以外が入力された場合の例外処理を追加する（補充題）
				// 価格での検索を実行
				List<ItemBean> list = dao.findByPrice(price);
				// 結果をリクエストスコープに登録して画面遷移
				request.setAttribute("items", list);
				this.gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("add")) {
				// nameキーとpriceキーを取得
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));	// TODO：数字以外が入力された場合の例外処理を追加する（補充題）
				// 商品追加処理を実行
				dao.addItem(name, price);
				// 確認用の全件検索
				List<ItemBean> list = dao.findAll();
				// 結果をリクエストスコープに登録して画面遷移
				request.setAttribute("items", list);
				this.gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("delete")) {
				// codeキーを取得
				int code = Integer.parseInt(request.getParameter("code"));	// TODO：数字以外が入力された場合の例外処理を追加する（補充題）
				// 商品削除処理を実行
				dao.delete(code);
				// 確認用の全件検索
				List<ItemBean> list = dao.findAll();
				// 結果をリクエストスコープに登録して画面遷移
				request.setAttribute("items", list);
				this.gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("update")) {
				// codeキーとpriceキーを取得
				int code = Integer.parseInt(request.getParameter("code"));
				int price = Integer.parseInt(request.getParameter("price"));
				// 商品の価格を変更
				dao.update(code, price);
				// 確認用の全件検索
				List<ItemBean> list = dao.findAll();
				// 結果をリクエストスコープに登録して画面遷移
				request.setAttribute("items", list);
				this.gotoPage(request, response, "/showItem.jsp");
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
