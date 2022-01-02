package test.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import app.bean.ItemBean;
import app.dao.ItemDAO;

class ItemDaoTest {

	/** テスト対象クラス：system under test */
	ItemDAO sut;

	/** テスト補助変数 */
	List<ItemBean> expectedList;
	List<ItemBean> actualList;

	@BeforeEach
	void setUp() throws Exception {
		// テスト対象クラスをインスタンス化
		sut = new ItemDAO();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	@DisplayName("ItemDAO#findAllメソッドのテストクラス")
	class findAll {
		@BeforeEach
		void setUp() {
			expectedList = new ArrayList<ItemBean>();
			expectedList.add(new ItemBean(1, "Javaの基本", 2500));
			expectedList.add(new ItemBean(2, "MLB Fun", 980));
			expectedList.add(new ItemBean(3, "料理BOOK!", 1200));
			expectedList.add(new ItemBean(4, "なつかしのアニメシリーズ", 2000));
			expectedList.add(new ItemBean(5, "The Racer", 1000));
			expectedList.add(new ItemBean(6, "Space Wars 3", 1800));
			expectedList.add(new ItemBean(7, "パズルゲーム", 780));
			expectedList.add(new ItemBean(8, "Invader Fighter", 3400));
			expectedList.add(new ItemBean(9, "Play the BascketBall", 2200));
		}
		@Test
		@DisplayName("【Test-11】itemテーブルのすべてのレコードを取得できる")
		void test_11() throws Exception {
			// setup
			// execute
			actualList = sut.findAll();
			// verify
			for (int i = 0; i < actualList.size(); i++) {
				assertThat(actualList.get(i).toString(), is(expectedList.get(i).toString()));
			}
		}
	}

	@Nested
	@DisplayName("ItemDAOのインスタンス化のテストケース")
	class istance {
		@Test
		@DisplayName("【Test-02】クラスフィールドconnを取得できる")
		void test_02() throws Exception {
			// setup
			// execute
			// verify
			assertThat(sut.getConnection(), is(instanceOf(Connection.class)));
		}
		@Test
		@DisplayName("【Test-01】ItemDAOをインスタンス化できる")
		void test_01() {
			// setup
			// execute
			// verify
			assertThat(sut, is(instanceOf(ItemDAO.class)));
		}
	}
}
