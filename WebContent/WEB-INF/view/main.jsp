<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="theme-color" content="#29343a">
<title>Tistory Editor</title>
<link rel="stylesheet" type="text/css" href="./css/blog/style.css">
<script src="https://code.jquery.com/jquery-1.12.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<header>
		<nav class="navbar navbar-inverse">
			<div class="navbar-header">
				<a class="navbar-brand" href="#" onclick="menu('left')"><span class="menuSpan">MENU</span></a> 
				<a class="navbar-brand title" href="/"><span>Tistory editor</span></a>
				<a class="navbar-brand" href="#" onclick="menu('right')"><span class="menuSpan">LIST</span></a>
			</div>
		</nav>
	</header>
	<section class="headerspace"></section>
	<aside id="leftside">
		<h2 class="side-header"><img src="" alt="[##_blogger_##]">[##_title_##]</h2>
		<div class="side-menu bs-glyphicons">
			<ul class="bs-glyphicons-list">
			<li onclick="location.href='/';">
				<span class="glyphicon glyphicon-home" aria-hidden="true" title="홈으로"></span>
				<span>홈으로</span>
			</li>
			<li onclick="location.href='mailto:nowonbun@naver.com';">
				<span class="glyphicon glyphicon-envelope" aria-hidden="true" title="메일보내기"></span>
				<span>메일발송</span>
			</li>
			<li onclick="window.external.AddFavorite('http://nowonbun.tistory.com','☆~명월일지~☆');">
				<span class="glyphicon glyphicon-star-empty" aria-hidden="true" title="즐겨찾기"></span>
				<span>즐겨찾기</span>
			</li>
			<li onclick="location.href='[##_guestbook_link_##]';">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true" title="방명록"></span>
				<span>방명록</span>
			</li>
			<li onclick="menu('close');">
				<span class="glyphicon glyphicon-remove" aria-hidden="true" title="메뉴닫기"></span>
				<span>메뉴닫기</span>
			</li>
			</ul>
		</div>
		<div class="side-list">
			<ul class="side-nav"></ul>
		</div>
		<div class="side-footer bs-glyphicons">
			<ul class="bs-glyphicons-list">
				<li onclick="login();" class='logout on'>
					<span class="glyphicon glyphicon-log-in" aria-hidden="true" title="로그인"></span>
					<span>로그인</span>
				</li>
				<li onclick="logout();" class='login off'>
					<span class="glyphicon glyphicon-log-out" aria-hidden="true" title="로그인"></span>
					<span>로그아웃</span>
				</li>
				<li onclick="addLink();" class='login off'>
					<span class="glyphicon glyphicon-list-alt" aria-hidden="true" title="링크추가"></span>
					<span>링크추가</span>
				</li>
				<li onclick="writeTistory();" class='admin off'>
					<span class="glyphicon glyphicon-leaf" aria-hidden="true" title="글쓰기"></span>
					<span>글쓰기</span>
				</li>
				<li onclick="adminTistory();" class='admin off'>
					<span class="glyphicon glyphicon-cog" aria-hidden="true" title="관리"></span>
					<span>관리</span>
				</li>
			</ul>
		</div>
	</aside>
	<aside id="rightside">
		<h2 class="side-header">
			<a href="#" onclick="menu('close');">
				<span class="glyphicon glyphicon-remove" aria-hidden="true" title="메뉴닫기"></span>
				<span>메뉴닫기</span>
			</a>
			<span>
				<s_search>
					<input type="text" name="[##_search_name_##]" value="[##_search_text_##]" onkeypress="if (event.keyCode == 13) { [##_search_onclick_submit_##] }"/>
					<input value="검색" type="button" onclick="[##_search_onclick_submit_##]" class="submit"/>
				</s_search>
			</span>
		</h2>
		<div id="recentList">
			<h3 class="rightsideTitle">최근에 올라온 글</h3>
			<ol>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ol>
		</div>
		<hr>
		<div id="categoryList">
			<h3 class="rightsideTitle">카테고리 다른글</h3>
			<ol>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ol>
		</div>
	</aside>
	<section class="backgroundLayout" onclick="menu('close');"></section>
	<main role="main" class="off">
	<!-- start:main -->
		<div id="main" class="container">
			<!-- 메인 -->
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<!-- ************************검색 결과 리스트************************-->
				<s_list>
				<div class="searchList">
					<h3><span>[##_list_conform_##]</span> 검색결과</h3>
					<p>검색 건수 : [##_list_count_##]건</p>
				</div>
				</s_list>
				<!-- ************************검색 결과 리스트************************-->
				<!-- ************************공지사항************************-->
				<s_notice_rep>
					<div class="entryNotice">
						<div class="titleWrap">
							<h2><a href="[##_notice_rep_link_##]">[##_notice_rep_title_##]</a></h2>
							<!--span class="date"> [##_notice_rep_date_##]</span-->
						</div>
						<hr class="titileHr">
						<div class="adsenseHeadDefault"></div>
						<div class="article">[##_notice_rep_desc_##]</div>
						<div class="adsenseFootDefault"></div>
					</div>
				</s_notice_rep>
				<!-- ************************공지사항************************-->
				<!-- ************************방명록************************-->
				<s_guest>
				<div class="guestbook">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<h3>방명록</h3>
						<!-- 2-5-1. 방명록 글쓰기 -->
						<s_guest_input_form>
						<div class="guestWrite">
							<s_guest_member> <s_guest_form>
							<div class="col-lg-1 col-md-2 col-sm-12 col-xs-12">
								<label for="name">name</label>
							</div>
							<div class="col-lg-2 col-md-10 col-sm-12 col-xs-12">
								<input type="text" name="[##_guest_input_name_##]" value="[##_guest_name_##]" class="form-control textField commenttext" placeholder="enter name" />
							</div>
							<div class="col-lg-1 col-md-2 col-sm-12 col-xs-12">
								<label for="password">password</label>
							</div>
							<div class="col-lg-2 col-md-10 col-sm-12 col-xs-12">
								<input type="password" name="[##_guest_input_password_##]" value="[##_guest_password_##]" class="form-control textField commenttext" maxlength="8" placeholder="password" />
							</div>
							<div class="col-lg-1 col-md-2 col-sm-12 col-xs-12">
								<label for="homepage">homepage</label>
							</div>
							<div class="col-lg-4 col-md-10 col-sm-12 col-xs-12">
								<input type="text" name="[##_guest_input_homepage_##]" value="[##_guest_homepage_##]" class="form-control textField commenttext" placeholder="enter name" />
							</div>
							<div class="col-lg-1 col-md-12 col-sm-12 col-xs-12">
								<div>
									<input id="[##_guest_input_is_secret_##]" type="checkbox" name="[##_guest_input_is_secret_##]" value="1" /> <label for="[##_guest_input_is_secret_##]"> 비밀글 </label>
								</div>
							</div>
							</s_guest_form> </s_guest_member>
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<textarea placeholder="enter comment" name="[##_guest_textarea_body_##]" class="form-control commenttext" rows="3" style="resize: none;"></textarea>
								<a class="btnComment pull-right" onclick="[##_guest_onclick_submit_##]" >방명록 쓰기</a>
							</div>
						</div>
						</s_guest_input_form>
					</div>
					<!-- 2-5-1. 방명록 글쓰기 -->
					<!-- 2-5-2. 방명록 리스트 -->
					<s_guest_container>
					<div class="guestList">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<ol class="reply list-unstyled">
								<s_guest_rep>
								<li id='[##_guest_rep_id_##]' class="commentList">
									<div class="[##_guest_rep_class_##]">
										<span class="name">[##_guest_rep_name_##]</span> <br /> <span class="date"> [##_guest_rep_date_##]</span> | <span class="control"> <a href="#" onclick="[##_guest_rep_onclick_delete_##]" class="modify commentA">&nbsp;<span>수정/삭제</span></a> <a href="#" onclick="[##_guest_rep_onclick_reply_##]" class="modify commentA">&nbsp;<span>댓글쓰기</span></a>
										</span>
										<hr class="commentHr">
										<p class="commentContents">[##_guest_rep_desc_##]</p>
									</div> <s_guest_reply_container>
									<ul>
										<s_guest_reply_rep>
										<li id='[##_guest_rep_id_##]'>
											<div class="[##_guest_rep_class_##]">
												<span class="name">[##_guest_rep_name_##]</span><br /> <span class="date"> [##_guest_rep_date_##]</span> | <span class="control"> <a href="#" onclick="[##_guest_rep_onclick_delete_##]" class="modify commentA"> &nbsp;<span>수정/삭제</span>
												</a>
												</span>
												<hr class="commentHr">
												<p class="commentContents">[##_guest_rep_desc_##]</p>
											</div>
										</li>
										</s_guest_reply_rep>
									</ul>
									</s_guest_reply_container>
								</li>
								</s_guest_rep>
							</ol>
						</div>
					</div>
					</s_guest_container>
					<!-- 2-5-2. 방명록 리스트 -->
				</div>
				</s_guest>
				<!-- ************************방명록************************-->
				<!-- ************************글************************-->
				<s_article_rep>
				<article class="entry">
					<s_index_article_rep>
						<div class="list-row pos-right ratio-fixed ratio-4by3 crop-center lts-narrow fouc clearfix searchListEntity">	
							<div class="list-body">
								<div class="flexbox">
									<a class="list-link" href="[##_article_rep_link_##]">
										<h3 class="list-head ie-nanum ci-link">[##_article_rep_title_##]</h3>
										<p class="list-summary">[##_article_rep_summary_##]</p>
									</a>
									<div class="list-meta ie-dotum">
										<a href="[##_article_rep_category_link_##]" class="p-category ci-color">[##_article_rep_category_##]</a>
										<span class="txt-bar"></span>
										<span class="timeago ff-h dt-published">[##_article_rep_date_##]</span>
									</div>
								</div>
							</div>
							<s_article_rep_thumbnail>
							<a class="has-object t-photo" href="[##_article_rep_link_##]">
								<div class="thumbnail">
									<div class="cropzone">
										<img src="[##_article_rep_thumbnail_url_##]">
									</div>
								</div>
							</a>
							</s_article_rep_thumbnail>
						</div>
					</s_index_article_rep>
					<s_permalink_article_rep>
						<!-- 글 관련 정보 : 글제목, 카테고리, 날짜, 관리자 -->
						<div class="titleArea">
							<div class="title">[##_article_rep_title_##]</div>
							<hr class="titileHr">
							<div class="categoryArea">
								<a href="[##_article_rep_category_link_##]">[##_article_rep_category_##]</a> &nbsp;[##_article_rep_date_##]
								<s_ad_div> &nbsp;|&nbsp; <a href="[##_s_ad_m_link_##]">Modify</a> : <a href="#" onclick="[##_s_ad_m_onclick_##]">Modify(new)</a> &nbsp;|&nbsp; ([##_s_ad_s1_label_##])→<a href="#" onclick="[##_s_ad_s2_onclick_##]">[##_s_ad_s2_label_##]</a> &nbsp;|&nbsp; <a href="#" onclick="[##_s_ad_t_onclick_##]">Trackback</a> &nbsp;|&nbsp; <a href="#" onclick="[##_s_ad_d_onclick_##]">Delete</a> </s_ad_div>
							</div>
						</div>
						<!-- 글 관련 정보 : 글제목, 카테고리, 날짜, 관리자 -->
						<!-- 블로그 글 -->
						<div class="adsenseHeadDefault"></div>
						<div class="article">[##_article_rep_desc_##]</div>
						<div class="adsenseFootDefault"></div>
						<!-- 블로그 글 -->
						<!-- 2-8-3. 글 관련 태그 -->
						<s_tag_label>
						<div class="tagTrail">
							<div class="tagText">[##_tag_label_rep_##]</div>
						</div>
						</s_tag_label>
						<!-- 2-8-3. 글 관련 태그 -->
						<hr>
						<!-- 2-8-4. 트랙백과 댓글 보이기/감추기 -->
						<div class="actionTrail ">
							<ul class="nav nav-tabs nav-justified">
								<li><a href="#commentList" onclick="commnet();"> 댓글 </a></li>
							</ul>
						</div>
						<!-- 2-8-4. 트랙백과 댓글 보이기/감추기 -->
						<div id="commentList" class="tab-content">
							<!-- 2-8-6. 댓글 -->
							<div class="commentCnt">
							댓글 <span class="cnt">[##_article_rep_rp_cnt_##]</span>개가 달렸습니다.
							</div>
							<s_rp>
								<div class="comment">
									<!-- 2-8-6-2. 댓글 쓰기 -->
									<s_rp_input_form>
									<div class="commentWrite col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<s_rp_member> <s_rp_guest>
										<div class="row commentSet">
											<div class="col-lg-1 col-md-2 col-sm-12 col-xs-12">
												<label for="name_20">name</label>
											</div>
											<div class="col-lg-4 col-md-10 col-sm-12 col-xs-12">
												<input type="text" id="name_20" name="name" value="" class="form-control textField commenttext" placeholder="enter name">
											</div>
											<div class="col-lg-1 col-md-2 col-sm-12 col-xs-12">
												<label for="password_20">password</label>
											</div>
											<div class="col-lg-4 col-md-10 col-sm-12 col-xs-12">
												<input type="password" id="password_20" class="form-control textField commenttext" maxlength="8" name="password" value="" placeholder="password">
											</div>
											<div class="col-lg-2 col-md-12 col-sm-12 col-xs-12">
												<div>
													<input id="secret_20" type="checkbox" name="secret" value="1" /> <label> 비밀글 </label>
												</div>
											</div>
										</div>
										</s_rp_guest> </s_rp_member>
										<textarea id="[##_rp_input_comment_##]_[##_article_rep_id_##]" placeholder="enter comment" name="[##_rp_input_comment_##]" class="form-control commenttext" rows="3" style="resize: none;"></textarea>
										<a class="btnComment pull-right" onclick="[##_rp_onclick_submit_##]">댓글쓰기</a>
									</div>
									</s_rp_input_form>
									<!-- 2-8-6-2. 댓글 쓰기 -->
									<!-- 2-8-6-1. 댓글 리스트 -->
									<s_rp_container>
									<div class="commentList col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<ol class="reply list-unstyled">
											<s_rp_rep>
											<li id='[##_rp_rep_id_##]' class="commentList">
												<div class="[##_rp_rep_class_##]">
													<span class="name">[##_rp_rep_name_##]</span><br /> <span class="date"> [##_rp_rep_date_##]</span> | <span class="control"> <a href="#" onclick="[##_rp_rep_onclick_delete_##]" class="modify commentA">&nbsp;<span>수정/삭제</span></a> <a href="#" onclick="[##_rp_rep_onclick_reply_##]" class="write commentA">&nbsp;<span>댓글쓰기</span></a>
													</span>
													<hr class="commentHr">
													<p class="commentContents">[##_rp_rep_desc_##]</p>
												</div> <s_rp2_container>
												<ul>
													<s_rp2_rep>
													<li id='[##_rp_rep_id_##]'>
														<div class="[##_rp_rep_class_##]">
															<span class="name">[##_rp_rep_name_##]</span><br /> <span class="date"> [##_rp_rep_date_##]</span> | <span class="control"> <a href="#" onclick="[##_rp_rep_onclick_delete_##]" class="modify">&nbsp;<span>수정/삭제</span></a>
															</span>
															<hr class="commentHr">
															<p class="commentContents">[##_rp_rep_desc_##]</p>
														</div>
													</li>
													</s_rp2_rep>
												</ul>
												</s_rp2_container>
											</li>
											</s_rp_rep>
										</ol>
									</div>
									</s_rp_container>
								<!-- 2-8-6-1. 댓글 리스트 -->
								</div>
							</s_rp>
							<!-- 2-8-6. 댓글 -->
						</div>
					</s_permalink_article_rep>
				</article>
				</s_article_rep>
				<!-- ************************글************************-->
			</div>
			<!-- 메인 -->
		</div>
	<!-- end:main -->
	</main>
	<!-- ************************2-9. 페이징************************ -->
	<s_paging>
		<section class="paging">
		<a [##_prev_page_##] class='glyphicon glyphicon-backward [##_no_more_prev_##]' aria-hidden='true' title='prev'></a>
	 		<span class="numbox"> 
				<s_paging_rep> 
					<a [##_paging_rep_link_##] class="num">[[##_paging_rep_link_num_##]]</a> 
				</s_paging_rep>
			</span> 
		<a [##_next_page_##] class='glyphicon glyphicon-forward [##_no_more_next_##]' aria-hidden='true' title='next'></a>
		</section>
	</s_paging>
	<!-- ************************2-9. 페이징************************ -->
	<!-- start:footer -->
	<footer>
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 footer-bottom">
			<!-- 방문자수 -->
			<p>
				<span class="total">Total : [##_count_total_##] </span>
				<span class="today">Today : [##_count_today_##]</span>
				<span class="yesterday">Yesterday : [##_count_yesterday_##]</span>
			</p>
			<p>Copyright © 2016 nowonbun.tistory.com</p>
		</div>
	</footer>
	<!-- end:footer -->
	<section style="display: none" id="template">
		<div id="adsenseHeader">
		<ins class="adsbygoogle" style="display:block" data-ad-client="ca-pub-6300064900388375" data-ad-slot="3977396448" data-ad-format="auto"></ins>
		</div>
		<div id="adsenseFooter">
			<ins class="adsbygoogle" style="display:block" data-ad-client="ca-pub-6300064900388375" data-ad-slot="6930862840" data-ad-format="auto"></ins>
		</div>
		<div id="categoryTemplate">[##_category_list_##]</div>
		<div id="blogImage">[##_image_##]</div>
		<div id="loginstate"></div>
		<s_sidebar>
			<s_sidebar_element>
				<!-- 최근에 올라온 글 -->
				<div class="recentPost">
					<h3>최근에 올라온 글 </h3>
					<ul>
						<s_rctps_rep>
							<li>
								<a href="[##_rctps_rep_link_##]"> [##_rctps_rep_title_##].</a> 
								<span class="cnt">[##_rctps_rep_rp_cnt_##]</span>
							</li>
						</s_rctps_rep>
					</ul>
				</div>
			</s_sidebar_element>
		</s_sidebar>
	</section>
	<script src="./js/blog/nowonbun.js"></script>
</body>
</html>