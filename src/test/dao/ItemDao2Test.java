package test.dao;



import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import app.bean.ItemBean;
import app.dao.ItemDAO2;

class ItemDao2Test {

	/** テスト対象クラス：system under test */
	ItemDAO2 sut;

	@BeforeEach
	void setUp() throws Exception {
		sut = new ItemDAO2();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

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
		assertThat(sut, is(instanceOf(ItemDAO2.class)));
	}

}
