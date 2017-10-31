<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!------------==============================  메세지 박스  ==============================------------>
		<div id="msg_box">
			<p>회사 Follow</p>
			<ul id="follow_list">
				<c:forEach items="${followcompanylist}" var="FollowCVO">
					<li><a
						href="../search/searchNews?code=${FollowCVO.followcode}"> <span
							class="profile_img"><img
								src="../resources/img/${FollowCVO.pimage}" /></span>
							<div class="follow">
								<div>
										<c:if test="${fn:length(FollowCVO.compname_kr) > 10}">
											<c:out value="${fn:substring(FollowCVO.compname_kr,0,10)}" />....
												<c:if test="${fn:length(FollowCVO.compname_en) > 10}">
													<br>(<c:out value="${fn:substring(FollowCVO.compname_en,0,10)}" />....)
           										</c:if>
													<br>(<c:out value="${FollowCVO.compname_en}" />)
										</c:if>
										<c:if test="${fn:length(FollowCVO.compname_kr) <10}">
											<c:out value="${FollowCVO.compname_kr}" />
												<br>
											(<c:out value="${FollowCVO.compname_en}" />)
									</c:if>
								</div>
							</div>
					</a></li>
				</c:forEach>
			</ul>
		</div>
		<div id="msg_box1">
			<p>개인 Follow</p>
			<ul id="follow_list1">
				<c:forEach items="${followlist}" var="FollowVO">
					<li><a
						href="../member/individualfollow?email=${FollowVO.followid}"> <span
							class="profile_img"><img
								src="../resources/img/${FollowVO.profimg}" /></span>

							<div class="follow">
								<div class="user">
								<c:if test="${fn:length(FollowVO.last) > 5}">
									${FollowVO.first}<c:out value="${fn:substring(FollowVO.last,0,5)}" />....
								</c:if>
								<c:if test="${fn:length(FollowVO.last) < 5}">
									${FollowVO.first}${FollowVO.last}
								</c:if><br>
								<c:if test="${fn:length(FollowVO.company) > 7}">
									<c:out value="${fn:substring(FollowVO.company,0,5)}" />....
								</c:if>
								<c:if test="${fn:length(FollowVO.company) < 7}">
									${FollowVO.company}
								</c:if>
								&nbsp;(${FollowVO.title})
									<c:if test="${FollowVO.new_post eq 'true'}">
										<div class="new">new</div>
									</c:if>
								</div>
							</div>
					</a></li>

				</c:forEach>
			</ul>
		</div>