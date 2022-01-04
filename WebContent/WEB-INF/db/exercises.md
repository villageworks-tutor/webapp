# 補充題

  - 補充題-1. ItemDAO.java:302行目　　データベース接続情報を定数にまとめる。
  - 補充題-2. ItemServlet.java：59行目　数字以外が入力された場合の例外処理を追加する。
  - 補充題-3. ItemServlet.java：68行目　数字以外が入力された場合の例外処理を追加する。
  - 補充題-4. ItemServlet.java：78行目　数字以外が入力された場合の例外処理を追加する。
  - 補充題-5. ItemServlet.java：68行目　DAOExceptionのメッセージに変更する。

また、以下の演習も挑戦してみましょう。

  - 演習題-1. ItemServlet#doGetメソッドにおいて、分岐した処理内で遷移URLを設定して遷移を実行しています。しかし、すべての分岐内で同じURLに遷移しているので冗長な記述になっています。そこで、遷移先URLを設定して遷移する部分を分岐外にまとめて記述するように変更してみましょう。

  - 演習題-2. showItem.jspでは、ボタンとリンクからItemServletを呼び出しているので、ItemDAO#doGetメソッドとItemDAO#doPostメソッドに分けて記述しました。このようにpost送信とget送信が混在する場合は、serviceメソッドで一つにまとめることができます。そこで、ItemServlet#doGet/ItemDAO#doPostメソッドをItemServlet#servletメソッドひとつにまとめてみましょう。

  - 演習題-3. ItemDAOのCRUD操作に対応するメソッドにおいて、実行されるSQLを変数で設定しています。これをクラス定数として設定し直してみましょう。この再設定に伴って、必要があればメソッドのコードも変更しましょう。


