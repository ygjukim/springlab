<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
<style type="text/css">
.content {
	text-align: center;
}

table {
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
}

tr, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<header>
		<h1 class="content">게시글 상세</h1>
		<h3 align="right">${user.name} 님, 환영합니다! &nbsp;
			<button type="button" onclick="location.href='logout'">로그아웃</button>
		</h3>
		<hr>
	</header>
	<article>
		<form action="updateBoard" method="POST">
			<table>
				<tr>
					<td bgcolor="orange" width="70" align="center">제목</td>
					<td align="left">
						<input type="text" name="title" value="${board.title}" />
					</td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70" align="center">작성자</td>
					<td align="left">${board.writer}</td>
				</tr>
				<tr>
					<td bgcolor="orange" width="70" align="center">내용</td>
					<td align="left">
						<textarea name="content" cols="40" rows="10">${board.content}</textarea>
					</td>
				</tr>
				<tr>	
					<td bgcolor="orange" width="70" align="center">등록일</td>
					<td align="left">${board.regDate}</td>
				</tr>
				<tr>	
					<td bgcolor="orange" width="70" align="center">조회수</td>
					<td align="left">${board.cnt}</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="글 수정" /></td>
				</tr>
			</table>
			<input type="hidden" name="seq" value="${board.seq}" />
			<input type="hidden" name="cnt" value="${board.cnt}" />
		</form>
		<hr>
		<div class="content">
			<button type="button" onclick="location.href='insertBoard'">글 등록</button>&nbsp;&nbsp;
			<button type="button" onclick="location.href='deleteBoard?seq=${board.seq}'">글 삭제</button>&nbsp;&nbsp;
			<button type="button" onclick="location.href='getBoardList'">글 목록</button>
		</div>
	</article>	
</body>
</html>