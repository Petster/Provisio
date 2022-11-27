<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:Layout>
  <t:CenteredSection>
    <div class="p-2 flex-grow flex flex-col">
      <div class="flex flex-col gap-2 content-center items-center justify-center color-3 p-8 rounded-lg">
        <h1 class="text-3xl">404 Page not found</h1>
        <p>We are sorry for the inconvenience, please click <a class="underline" href="index.jsp">Here</a> to return to the homepage.</p>
      </div>
    </div>
  </t:CenteredSection>
</t:Layout>