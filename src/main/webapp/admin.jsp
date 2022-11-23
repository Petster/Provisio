<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.LoggedIn.admin != true}">
  <c:redirect url="/index.jsp"/>
</c:if>
<t:Layout>
  <div class="p-2 flex-grow flex flex-col gap-4">
    <div class="flex flex-row p-2 justify-between items-center content-center mx-auto gap-4">
      <button class="flex flex-grow color-4 text-white p-3 rounded-md" id="resetDatabase">Reset Database</button>
      <button class="flex flex-grow color-4 text-white p-3 rounded-md" id="dummyData">Create Dummy Data</button>
    </div>
    <div class="flex flex-col flex-grow color-3 p-2 rounded-lg">
      <div class="flex flex-col content-center items-center justify-center ">
        <h1 class="text-xl">Create News Article</h1>
        <form id="createNews" class="flex flex-col flex-grow w-5/6 gap-4">
          <div class="flex flex-col">
            <label for="newsTitle">Title</label>
            <input type="text" id="newsTitle" name="newsTitle" />
          </div>
          <div class="flex flex-col">
            <label for="newsImg">Image Link</label>
            <input type="text" id="newsImg" name="newsImg" />
          </div>
          <div class="flex flex-col">
            <label for="newsDesc">Description</label>
            <input type="text" id="newsDesc" name="newsDesc" />
          </div>
          <div class="flex flex-col">
            <button id="newsCreate" name="newsCreate" type="button" class="color-4 rounded-lg text-white font-bold p-2">Create News</button>
          </div>
        </form>
      </div>
    </div>
    <div class="flex flex-col flex-grow color-3 p-2 rounded-lg">
      <div class="flex flex-col content-center items-center justify-center ">
        <h1 class="text-xl">Create Room</h1>
        <form id="createRoom" class="flex flex-col flex-grow w-5/6 gap-4">
          <div class="flex flex-col">
            <label for="roomTitle">Title</label>
            <input type="text" id="roomTitle" name="roomTitle" />
          </div>
          <div class="flex flex-col">
            <label for="newsDesc">Ammenities</label>
            <div class="flex flex-row gap-4">
              <div class="flex flex-col">
                <label for="breakfast">Breakfast</label>
                <input type="checkbox" name="breakfast" id="breakfast" />
              </div>
              <div class="flex flex-col">
                <label for="wifi">Wi-Fi</label>
                <input type="checkbox" name="wifi" id="wifi" />
              </div>
              <div class="flex flex-col">
                <label for="fitness">Fitness</label>
                <input type="checkbox" name="fitness" id="fitness" />
              </div>
              <div class="flex flex-col">
                <label for="store">Store</label>
                <input type="checkbox" name="store" id="store" />
              </div>
              <div class="flex flex-col">
                <label for="nosmoke">No Smoking</label>
                <input type="checkbox" name="nosmoke" id="nosmoke" />
              </div>
              <div class="flex flex-col">
                <label for="mobile">Mobile</label>
                <input type="checkbox" name="mobile" id="mobile" />
              </div>
            </div>
          </div>
          <div class="flex flex-col">
            <label for="roomPrice">Price</label>
            <input type="text" id="roomPrice" name="roomPrice" />
          </div>
          <div class="flex flex-col">
            <label for="roomLoyalty">Loyalty Point Value</label>
            <input type="text" id="roomLoyalty" name="roomLoyalty" />
          </div>
          <div class="flex flex-col">
            <label for="roomImg">Image (Class)</label>
            <input type="text" id="roomImg" name="roomImg" />
          </div>
          <div class="flex flex-col">
            <label for="roomDesc">Room Highlights</label>
            <textarea id="roomDesc" name="roomDesc"></textarea>
          </div>
          <div class="flex flex-col">
            <button id="roomCreate" name="roomCreate" type="button" class="color-4 rounded-lg text-white font-bold p-2">Create Room</button>
          </div>
        </form>
      </div>
    </div>
  </div>
  <script>
    $('#newsCreate').click(function(e) {
      e.preventDefault();
      $.ajax({
        type: 'post',
        url: 'Admin?cNews',
        data: $('#createNews').serialize()
      }).then((result) => {
        if(result.success) {
          swal({
            title: "Success",
            text: result.msg,
            icon: "success",
          });
        } else {
          swal({
            title: "Error",
            text: result.msg,
            icon: "error",
          });
        }
      })
    });

    $('#roomCreate').click(function(e) {
      e.preventDefault();
      $.ajax({
        type: 'post',
        url: 'Admin?cRoom',
        data: $('#createRoom').serialize()
      }).then((result) => {
        if(result.success) {
          swal({
            title: "Success",
            text: result.msg,
            icon: "success",
          });
        } else {
          swal({
            title: "Error",
            text: result.msg,
            icon: "error",
          });
        }
      })
    });
    $('#resetDatabase').click(function(e) {
      e.preventDefault();
      $.ajax({
        type: 'post',
        url: 'ResetDatabase',
        success: function(response) {
          swal({
            title: "Success",
            text: "Database Reset",
            icon: "success",
          });
        },
        error: function(response) {
          swal({
            title: "Error",
            text: "Failed to Reset Database",
            icon: "error",
          });
        }
      })
    });

    $('#dummyData').click(function(e) {
      e.preventDefault();
      $.ajax({
        type: 'post',
        url: 'DummyData',
        success: function(response) {
          swal({
            title: "Success",
            text: "Dummy Data Created",
            icon: "success",
          });
        },
        error: function(response) {
          swal({
            title: "Error",
            text: `Failed to Create Dummy Data ${response}`,
            icon: "error",
          });
        }
      })
    });
  </script>
</t:Layout>