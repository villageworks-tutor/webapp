package test.dao;



import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import app.bean.ItemBean;
import app.dao.ItemDAO;

class ItemDao2Test {

	/** テスト対象クラス：system under test */
	ItemDAO sut;

	@BeforeEach
	void setUp() throws Exception {
		sut = new ItemDAO();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	@DisplayName("ItemDAO2#updateメソッドのテストクラス")
	class update {
		/** テスト補助変数 */
		List<ItemBean> expectedList = null;
		List<ItemBean> actualList = null;

		@Test
		@DisplayName("【Test-01】商品番号「9」の商品の価格を「1900」に変更できる")
		void test_01() throws Exception {
			// setup
			int code = 9;
			int price = 1900;
			expectedList = new ArrayList<ItemBean>();
			expectedList.add(new ItemBean(1, "Javaの基本", 2500));
			expectedList.add(new ItemBean(2, "MLB Fun", 980));
			expectedList.add(new ItemBean(3, "料理BOOK!", 1200));
			expectedList.add(new ItemBean(4, "なつかしのアニメシリーズ", 2000));
			expectedList.add(new ItemBean(5, "The Racer", 1000));
			expectedList.add(new ItemBean(6, "Space Wars 3", 1800));
			expectedList.add(new ItemBean(7, "パズルゲーム", 780));
			expectedList.add(new ItemBean(8, "Invader Fighter", 3400));
			expectedList.add(new ItemBean(9, "Play the BascketBall", 1900));
			// execute
			sut.update(code, price);
			actualList = sut.findAll();
			// verify
			for (int i = 0; i < actualList.size(); i++) {
				assertThat(actualList.get(i).toString(), is(expectedList.get(i).toString()));
			}

		}
	}

	@Disabled
	@Nested
	@DisplayName("ItemDAO2#deleteメソッドのテストクラス")
	class delete {
		/** テスト補助変数 */
		List<ItemBean> expectedList = null;
		List<ItemBean> actualList = null;

		@BeforeEach
		void setUp() {

		}

		@Test
		@DisplayName("【Test-01】商品番号10の商品を削除できる")
		void test_01() throws Exception {
			// setup
			int target = 10;
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
			// execute
			sut.delete(target);
			actualList = sut.findAll();
			// verify
			for (int i = 0; i < actualList.size(); i++) {
				assertThat(actualList.get(i).toString(), is(expectedList.get(i).toString()));
			}
		}
	}

	@Disabled
	@Nested
	@DisplayName("ItemDAO2#findByPriceメソッドのテストクラス")
	class findByPrice {
		/** テスト補助変数 */
		List<ItemBean> expectedList;
		List<ItemBean> actualList;

		@BeforeEach
		void setUp() {
			expectedList = new ArrayList<ItemBean>();
			expectedList.add(new ItemBean(8, "Invader Fighter", 3400));
			expectedList.add(new ItemBean(1, "Javaの基本", 2500));
			expectedList.add(new ItemBean(9, "Play the BascketBall", 2200));
			expectedList.add(new ItemBean(4, "なつかしのアニメシリーズ", 2000));
			expectedList.add(new ItemBean(6, "Space Wars 3", 1800));
			expectedList.add(new ItemBean(3, "料理BOOK!", 1200));
			expectedList.add(new ItemBean(5, "The Racer", 1000));
			expectedList.add(new ItemBean(2, "MLB Fun", 980));
			expectedList.add(new ItemBean(7, "パズルゲーム", 780));
		}

		@AfterEach
		void tearDown() {
		}

		@Test
		@DisplayName("【Test-02】500円以下の商品は0件である")
		void test_02() throws Exception {
			// setup
			int upperPrice = 500;
			expectedList = new ArrayList<ItemBean>();
			// execute
			actualList = sut.findByPrice(upperPrice);
			// verfy
			assertThat(actualList, is(expectedList));
		}

		@Test
		@DisplayName("【Test-01】1000円以下の商品を取得できる")
		void test_01() throws Exception {
			// setup
			int upperPrice = 1000;
			expectedList = new ArrayList<ItemBean>();
			expectedList.add(new ItemBean(2, "MLB Fun", 980));
			expectedList.add(new ItemBean(5, "The Racer", 1000));
			expectedList.add(new ItemBean(7, "パズルゲーム", 780));
			// execute
			actualList = sut.findByPrice(upperPrice);
			// verify
			for (int i = 0; i < actualList.size(); i++) {
				assertThat(actualList.get(i).toString(), is(expectedList.get(i).toString()));
			}
		}
	}

	@Disabled
	@Nested
	@DisplayName("ItemDAO2#addItemメソッドのテストクラス")
	class addItem {

		@Test
		@DisplayName("【Test-01】DVD「私を野球に連れてって」を追加できる")
		void test_01() throws Exception {
			// setup
			String name = "私を野球に連れてって";
			int prce = 1280;
			List<ItemBean> expectedList = new ArrayList<ItemBean>();
			expectedList.add(new ItemBean(1, "Javaの基本", 2500));
			expectedList.add(new ItemBean(2, "MLB Fun", 980));
			expectedList.add(new ItemBean(3, "料理BOOK!", 1200));
			expectedList.add(new ItemBean(4, "なつかしのアニメシリーズ", 2000));
			expectedList.add(new ItemBean(5, "The Racer", 1000));
			expectedList.add(new ItemBean(6, "Space Wars 3", 1800));
			expectedList.add(new ItemBean(7, "パズルゲーム", 780));
			expectedList.add(new ItemBean(8, "Invader Fighter", 3400));
			expectedList.add(new ItemBean(9, "Play the BascketBall", 2200));
			expectedList.add(new ItemBean(10, "私を野球に連れてって", 1280));
			// execute
			sut.addItem(name, prce);
			List<ItemBean> actualList = sut.findAll();
			// verify
			for (int i = 0; i < actualList.size(); i++) {
				assertThat(actualList.get(i).toString(), is(expectedList.get(i).toString()));
			}
		}
	}

	@Disabled
	@Nested
	@DisplayName("ItemDAO2#sortPriceメソッドのテストクラス")
	class sortPrice {
		/** テスト補助変数 */
		List<ItemBean> expectedListByAsc;
		List<ItemBean> expectedListByDesc;
		List<ItemBean> actualList;
		@BeforeEach
		void setUp() {
			// 価格の低い順
			expectedListByAsc = new ArrayList<ItemBean>();
			expectedListByAsc.add(new ItemBean(7, "パズルゲーム", 780));
			expectedListByAsc.add(new ItemBean(2, "MLB Fun", 980));
			expectedListByAsc.add(new ItemBean(5, "The Racer", 1000));
			expectedListByAsc.add(new ItemBean(3, "料理BOOK!", 1200));
			expectedListByAsc.add(new ItemBean(6, "Space Wars 3", 1800));
			expectedListByAsc.add(new ItemBean(4, "なつかしのアニメシリーズ", 2000));
			expectedListByAsc.add(new ItemBean(9, "Play the BascketBall", 2200));
			expectedListByAsc.add(new ItemBean(1, "Javaの基本", 2500));
			expectedListByAsc.add(new ItemBean(8, "Invader Fighter", 3400));
			// 価格の高い順
			expectedListByDesc = new ArrayList<ItemBean>();
			expectedListByDesc.add(new ItemBean(8, "Invader Fighter", 3400));
			expectedListByDesc.add(new ItemBean(1, "Javaの基本", 2500));
			expectedListByDesc.add(new ItemBean(9, "Play the BascketBall", 2200));
			expectedListByDesc.add(new ItemBean(4, "なつかしのアニメシリーズ", 2000));
			expectedListByDesc.add(new ItemBean(6, "Space Wars 3", 1800));
			expectedListByDesc.add(new ItemBean(3, "料理BOOK!", 1200));
			expectedListByDesc.add(new ItemBean(5, "The Racer", 1000));
			expectedListByDesc.add(new ItemBean(2, "MLB Fun", 980));
			expectedListByDesc.add(new ItemBean(7, "パズルゲーム", 780));
		}
		@AfterEach
		void tearDown() {
		}

		@Test
		@DisplayName("【Test-02】価格の高い順に全商品を取得できる")
		void test_02() throws Exception {
			// setup
			boolean isAscending = false;
			// execute
			actualList = sut.sortPrice(isAscending);
			// verify
			for (int i = 0; i < actualList.size(); i++) {
				assertThat(actualList.get(i).toString(), is(expectedListByDesc.get(i).toString()));
			}
		}

		@Test
		@DisplayName("【Test-01】価格の低い順に全商品を取得できる")
		void test_01() throws Exception {
			// setup
			boolean isAscending = true;
			// execute
			actualList = sut.sortPrice(isAscending);
			// verify
			for (int i = 0; i < actualList.size(); i++) {
				assertThat(actualList.get(i).toString(), is(expectedListByAsc.get(i).toString()));
			}
		}
	}

	@Test
	@DisplayName("ItemDAO2のインスタンス化")
	void test_00() {
		assertThat(sut, is(instanceOf(ItemDAO.class)));
	}

}
