<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@tag description="Page Header" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
        <div id="desktop" class="hidden h-14 color-2 py-1 px-2 md:flex flex-row justify-between items-center content-center header-border ">
            <a href="index.jsp" class="flex flex-row">
                <img src="img/LogoBlackSquareTransparent.png" alt="Provisio Logo" class="w-48" />
            </a>
            <div class="flex flex-row gap-4">
                <a href="about.jsp">About Us</a>
                <a href="reserve.jsp">Reserve</a>
                <a href="lookup.jsp">Reservation Lookup</a>
                <a href="contact.jsp">Contact Us</a>
            </div>
            <div class="flex flex-row gap-2">
                <a href="register.jsp">Join</a>
                <div class="border-r-2 border-black"></div>
                <div class="flex flex-row gap-2">
                    <a href="login.jsp">Sign in</a>
                    <div id="desktopToggle">
                        <i class="fa-solid fa-circle-user fa-xl"></i>
                    </div>
                </div>
            </div>
            <div id="desktopDropdown" class="hidden origin-top-right absolute right-2 top-14 z-50 mt-2 w-56 rounded-md color-3 p-2 flex flex-col">
                <div class="flex flex-row flex-grow gap-2 content-center items-center justify-center">
                    <div class="color-1 text-white py-1 px-2 text-xs rounded-md flex flex-grow justify-center color-5-hover cursor-pointer ">
                        <a href="myaccount.jsp">My Account</a>
                    </div>
                    <div class="color-1 text-white py-1 px-2 text-xs rounded-md flex flex-grow justify-center color-5-hover cursor-pointer ">
                        <a href="reserve.jsp">Book a Room</a>
                    </div>
                </div>
                <div class="flex flex-row flex-grow justify-between items-center content-center color-1 text-white py-1 px-2 text-xs rounded-md mt-2 color-5-hover cursor-pointer ">
                    <p><i class="fa-solid fa-right-from-bracket fa-flip-horizontal fa-lg"></i></p>
                    <p>Logout</p>
                </div>
            </div>
        </div>
        <div id="mobile" class="md:hidden h-14 color-2 py-1 px-2 flex flex-row justify-between items-center content-center header-border ">
            <div id="mobileToggle" class="flex flex-row p-2 cursor-pointer">
                <p><i id="mobileToggleIcon" class="fa-solid fa-bars fa-xl"></i></p>
            </div>
            <a href="index.jsp" class="flex flex-row">
                <img src="img/LogoBlackSquareTransparent.png" alt="Provisio Logo" class="w-48" />
            </a>
            <div id="userToggle" class="flex flex-row p-2 cursor-pointer">
                <p><i class="fa-solid fa-circle-user fa-xl"></i></p>
            </div>
        </div>
        <div id="mobileDropdown" class="md:hidden absolute z-50 w-full color-2 hidden flex flex-col ">
            <a href="about.jsp" class="h-14 p-2 flex flex-row content-center items-center justify-between header-border color-3-hover cursor-pointer hover:text-zinc-700">
                <div><i class="fa-solid fa-circle-info fa-2x"></i></div>
                <div><p>About Us</p></div>
            </a>
            <a href="reserve.jsp" class="h-14 p-2 flex flex-row content-center items-center justify-between header-border color-3-hover cursor-pointer hover:text-zinc-700">
                <div><i class="fa-solid fa-calendar fa-2x"></i></div>
                <div><p>Reserve</p></div>
            </a>
            <a href="lookup.jsp" class="h-14 p-2 flex flex-row content-center items-center justify-between header-border color-3-hover cursor-pointer hover:text-zinc-700">
                <div><i class="fa-solid fa-magnifying-glass fa-2x"></i></div>
                <div><p>Reservation Lookup</p></div>
            </a>
            <a href="contact.jsp" class="h-14 p-2 flex flex-row content-center items-center justify-between header-border color-3-hover cursor-pointer hover:text-zinc-700">
                <div><i class="fa-solid fa-phone fa-2x"></i></div>
                <div><p>Contact Us</p></div>
            </a>
        </div>
        <div id="userDropdown" class="md:hidden absolute z-50 w-full color-2 hidden flex flex-col ">
            <a href="login.jsp" class="h-14 p-2 flex flex-row content-center items-center justify-between header-border color-3-hover cursor-pointer hover:text-zinc-700">
                <div><i class="fa-solid fa-users fa-2x"></i></div>
                <div><p>Join</p></div>
            </a>
            <a href="register.jsp" class="h-14 p-2 flex flex-row content-center items-center justify-between header-border color-3-hover cursor-pointer hover:text-zinc-700">
                <div><i class="fa-solid fa-sign-in fa-2x"></i></div>
                <div><p>Sign In</p></div>
            </a>
        </div>
    </header>