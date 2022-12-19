/**
 * index.js
 */

$('.btnBoard').on('click', function(){
	$('#section').load('/board/board_select');
});
//mapping 정보다.경로명이 아니라...

$('.btnGuestBook').on('click', function(){
	$('#section').load('/guestbook/guestbook_select');
});
/*$('.guestbook').ready(function(){
	$('.guestbook').load("/guestbook/guestbook10");
})*/

/* 방명록 최근 10개 -------------*/
$('#section>.guestbook').load("/guestbook/guestbook10");

/* 게시물 최근 10개 -------------*/ 
$('#section>.board').load("/board/board10");
//board/board10이라는 controller를 부르겠다



