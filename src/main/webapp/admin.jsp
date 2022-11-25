<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.LoggedIn == null}">
  <c:redirect url="/login.jsp?next=admin"/>
</c:if>
<c:if test="${sessionScope.LoggedIn.admin != true}">
  <c:redirect url="/index.jsp"/>
</c:if>
<t:Layout>
  <t:NormalSection>
  <div class="p-2 flex-grow flex flex-col gap-4 accordion" id="accordionExample">

    <div class="accordion-item" bis_skin_checked="1">
      <h2 class="accordion-header" id="head1">
        <button class="accordion-button bg-green-800 text-white hover:text-black hover:bg-green-600 collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="false" aria-controls="collapse1">
          Database Tools
        </button>
      </h2>
      <div id="collapse1" class="accordion-collapse collapse " aria-labelledby="head1"
           data-bs-parent="#accordionExample" bis_skin_checked="1">
        <div class="accordion-body" bis_skin_checked="1">
          <div class="flex flex-row gap-4 p-4">
            <button class="flex flex-grow color-4 text-white p-3 rounded-md" id="resetDatabase">Reset Database</button>
            <button class="flex flex-grow color-4 text-white p-3 rounded-md" id="dummyData">Create Dummy Data</button>
          </div>
        </div>
      </div>
    </div>

    <div class="accordion-item" bis_skin_checked="21">
      <h2 class="accordion-header" id="head2">
        <button class="accordion-button bg-green-800 text-white hover:text-black hover:bg-green-600 collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse2" aria-expanded="false" aria-controls="collapse2">
          News Article
        </button>
      </h2>
      <div id="collapse2" class="accordion-collapse collapse " aria-labelledby="head2"
           data-bs-parent="#accordionExample" bis_skin_checked="1">
        <div class="accordion-body" bis_skin_checked="1">
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
        </div>
      </div>
    </div>

    <div class="accordion-item" bis_skin_checked="3">
      <h2 class="accordion-header" id="head3">
        <button class="accordion-button bg-green-800 text-white hover:text-black hover:bg-green-600 collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse3" aria-expanded="false" aria-controls="collapse3">
          Room Creation
        </button>
      </h2>
      <div id="collapse3" class="accordion-collapse collapse " aria-labelledby="head3"
           data-bs-parent="#accordionExample" bis_skin_checked="1">
        <div class="accordion-body" bis_skin_checked="1">
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
      </div>
    </div>

    <div class="accordion-item" bis_skin_checked="4">
      <h2 class="accordion-header" id="head4">
        <button class="accordion-button bg-green-800 text-white hover:text-black hover:bg-green-600 collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse4" aria-expanded="false" aria-controls="collapse4">
          Location Creation
        </button>
      </h2>
      <div id="collapse4" class="accordion-collapse collapse " aria-labelledby="head4"
           data-bs-parent="#accordionExample" bis_skin_checked="1">
        <div class="accordion-body" bis_skin_checked="1">
          <div class="flex flex-col flex-grow color-3 p-2 rounded-lg">
            <div class="flex flex-col content-center items-center justify-center ">
              <h1 class="text-xl">Create Location</h1>
              <form id="createLocation" class="flex flex-col flex-grow w-5/6 gap-4">
                <div class="flex flex-col">
                  <label for="locationTitle">Title</label>
                  <input type="text" id="locationTitle" name="locationTitle" />
                </div>
                <div class="flex flex-col">
                  <label for="locationAddress">Address</label>
                  <input type="text" id="locationAddress" name="locationAddress" placeholder="Street, City, State" />
                </div>
                <div class="flex flex-col">
                  <button id="locationCreate" name="locationCreate" type="button" class="color-4 rounded-lg text-white font-bold p-2">Create Location</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="js/adminAjax.js"></script>
  </t:NormalSection>
</t:Layout>