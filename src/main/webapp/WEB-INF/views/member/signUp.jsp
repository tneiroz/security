<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="container">
   <div class="jumbotron my-3">
      <h3>회원가입 </h3>
   </div>
   <form action="${contextPath}/member/signUp" method="post" id="signForm">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      <div class="form-group">
           <label><small><b>이름</b></small></label>
           <input type="text" name="userName" class="form-control" placeholder="한글 이름 입력" required />
        </div>
       <div class="form-group">
           <label><small><b>아이디</b></small></label>
           <p><small>6~12자리 영문, 숫자로 입력</small></p>
           <div class="row">
              <div class="col-sm-8">
                 <input type="text" name="userId" class="form-control" placeholder="아이디 입력" required readonly="readonly"/>
              </div>
              <div class="col-sm-4">
                 <button type="button" class="btn btn-primary idSelectBtn">아이디 중복확인</button>
              </div>
           </div>
       </div>
       <div class="form-group">
           <label><small><b>비밀번호 </b></small></label>
           <p><small>영문+숫자+특수문자(@~!#$%^&*()\-=+,.?) 8자~20자 이내 입력 가능, 4자리 이상 동일 또는 연속된 숫자/문자열 입력 불가</small></p>
           <input type="password" name="userPw" class="form-control" placeholder="비밀번호 입력" required/>
       </div>
       <div class="form-group">
           <label><small><b>비밀번호 확인</b></small></label>
           <input type="password" name="confirmUserPw" class="form-control" required/>
       </div>
       <div class="form-group">
           <label><small><b>생년월일(YYYY.MM.DD.)</b></small></label>
           <input type="text" name="dateOfBirth" class="form-control" placeholder="YYYY.MM.DD." required/>
       </div>
       <div class="d-flex mb-3">
         <div class="form-check">
            <label class="form-check-label">
               <input type="radio" class="form-check-input" name="gender" value="MALE" required>남자
            </label>
         </div>
         <div class="form-check ml-3">
            <label class="form-check-label">
               <input type="radio" class="form-check-input" name="gender" value="FEMALE">여자
            </label>
         </div>
      </div>
      <div class="form-group">
           <label><small><b>이메일 주소</b></small></label>
           <input type="email" name="email" class="form-control" placeholder="예) abc@example.com"/>
       </div>
       <div class="form-group">
           <label><small><b>휴대전화 번호</b></small></label>
           <input type="text" name="phoneNumber" class="form-control" />
       </div>
       <div class="card">
          <div class="card-header">
             <strong>마케팅 및 광고 활용 동의 (선택)</strong>
          </div>
          <div class="card-body">
             <div class="d-flex justify-content-between mb-3">
               <div class="form-check">
                  <label class="form-check-label">
                     <input type="checkbox" class="form-check-input">
                     뉴스레터 (회원 소식지 등)
                  </label>
               </div>
               <div class="form-check">
                  <label class="form-check-label">
                     <input type="checkbox" class="form-check-input" >
                     프로모션 정보 (이벤트, 할인 쿠폰 등)
                  </label>
               </div>
               <div class="form-check">
                  <label class="form-check-label">
                     <input type="checkbox" class="form-check-input" >
                     SMS 수신 동의
                  </label>
               </div>
            </div>
          </div>
       </div>
       
       <div class="mt-3 d-flex justify-content-center">
          <div class="form-group">
             <button type="button" class="form-control btn-lg prevBtn">이전</button>
          </div>
             <div class="form-group ml-3">
             <button class="form-control btn btn-dark btn-lg">확인</button>
          </div>
       </div>
   </form>   
</div>

<div class="fade modal" id="select_id">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">아이디 중복 확인</h4>
          <button type="button" class="close" data-dismiss="modal">×</button>
        </div>
        <div class="modal-body">
           <div class="form-group">
              <input type="text" class="userId form-control">
           </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary findId">조회</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
</div>


<%@ include file="../layout/footer.jsp" %>
<script>
$(function(){
   console.log(contextPath)
   $('.prevBtn').on('click',function(){
      window.history.back();
   });
   // 아이디 중복 조회 모달창 
   $('.idSelectBtn').on('click',function(){
      $('#select_id').find('.userId').val('');
      $('#select_id').modal('show');
   });
   
   // 아이디 중복 조회
   $('.findId').on('click',function(){
      let userId = $('#select_id').find('.userId').val();
      if(userId.trim()=='' || userId==null) {
         alert('입력하세요')
         return; 
      }
      let url = contextPath + "/member/idCheck/" + userId;
      $.getJSON(url,function(result){
         if(result){ // 사용가능
            alert('사용가능한 아이디 입니다.')
            $('#signForm').find('input[name="userId"]').val(userId);
            $('#select_id').modal('hide');
         } else { // 사용 불가능
            alert('아이디 중복입니다. 사용 할 수 없습니다.')
         }         
      }).fail(function(){
         alert('잠시 후 다시 시도 해주세요')
      });
   })
})

</script>