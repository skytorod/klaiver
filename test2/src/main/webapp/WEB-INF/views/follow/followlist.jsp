<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!------------==============================  메세지 박스  ==============================------------>
		<div id="msg_box">
			<p>Follow</p>
			<ul id="follow_list">
				<c:forEach items="${followlist}" var="FollowVO">
					<li><a
						href="../search/searchNews?userid=${FollowVO.followid}"> <span
							class="profile_img"><img
								src="../resources/img/${FollowVO.profimg}" /></span>

							<div class="follow">
								<div class="user">
								${FollowVO.username}&nbsp;(${FollowVO.email})
									<c:if test="${FollowVO.new_post eq 'true'}">
										<div class="new">new</div>
									</c:if>
								</div>
							</div>
					</a></li>

				</c:forEach>
			</ul>
		</div>