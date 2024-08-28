<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1">
<base href="../" />

<title>HRMS</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
	integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ace-font.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ace-themes.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/ace.css">

<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/assets/favicon.png" />
<style>
body::before {
	font-family: "Source Code Pro", "SF Mono", Monaco, Inconsolata,
		"Fira Mono", "Droid Sans Mono", monospace, monospace;
	white-space: pre;
	display: block;
	padding: 1em;
	margin-bottom: 1em;
	border-bottom: 2px solid black;
}
/** the chat dialog appear animation **/
@
keyframes chatAppear { 70% {
	transform: translateY(-20%);
}

80










%
{
transform










:










translateY








(










-20








%
)








;
}
100










%
{
opacity










:










1








;
transform










:










none








;
}
}
.animation-appear {
	opacity: 0;
	transform: translateY(75%);
	animation: 750ms chatAppear;
	animation-delay: 1.5s;
	animation-fill-mode: forwards;
	transform-origin: bottom center;
}

@media screen and (prefers-reduced-motion: reduce) {
	.animation-appear {
		animation-duration: 1ms;
	}
}
/* for .btn-light-* buttons use a ripple based on `currenColor` */
.waves-effect:not(.waves-light) .waves-ripple {
	color: inherit;
	background: radial-gradient(transparent 0, currentColor 100%);
}

/* and make `currenColor` lighter by adding a white-ish layer over it*/
.waves-effect:not(.waves-light) .waves-ripple:after {
	content: "";
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	background: radial-gradient(transparent 0, rgba(255, 255, 255, 0.4) 100%);
}

.card {
	overflow: hidden;
}

/* the loading/progressing bar animation */
@
keyframes card-progress-frames { 0%{
	transform: translateX(-100%)
}

15










%
{
transform










:










translateX








(










0








%
)










scaleX








(










0








.3










)










}
40










%
{
transform










:










translateX








(










55








%
)










scaleX








(










0








.1










)










}
85










%
{
transform










:










translateX








(










140








%
)










scaleX








(










0








.6










)










}
100










%
{
transform










:










translateX








(










100








%
)










}
}
.card-progress {
	overflow: hidden;
	height: 4px;
	border-radius: 8px 8px 0 0;
}

.card-progressbar {
	height: inherit;
	transform-origin: left;
	animation: card-progress-frames 2.2s infinite linear;
	min-width: 360px;
}

/* Disable on IE */
@media screen and (-ms-high-contrast: active) , ( -ms-high-contrast :
	none) {
	.card-progressbar {
		animation: none;
	}
}

/* some styling for chartist chart */
.ct-chart>svg {
	position: relative !important;
}

.ct-chart::before, .ct-chart::after {
	display: none !important;
}

.ct-series-a .ct-line, .ct-series-a .ct-point {
	stroke: transparent;
}

.ct-series-a .ct-area, .ct-series-a .ct-slice-donut-solid, .ct-series-a .ct-slice-pie
	{
	fill: #de88af;
	fill-opacity: 0.75;
}

.ct-series-b .ct-line, .ct-series-b .ct-point {
	stroke: transparent;
}

.ct-series-b .ct-area, .ct-series-b .ct-slice-donut-solid, .ct-series-b .ct-slice-pie
	{
	fill: #55c0f6;
	fill-opacity: 0.75;
}

.ct-grid {
	stroke: rgba(221, 186, 206, 0.93);
	stroke-dasharray: 0;
}

.ct-series-c .ct-line, .ct-series-c .ct-point {
	stroke: transparent;
}

.ct-series-c .ct-area, .ct-series-c .ct-slice-donut-solid, .ct-series-c .ct-slice-pie
	{
	fill: #f6cb55;
	fill-opacity: 0.75;
}

.body-container {
	background-color: #221d2e;
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center;
	background-attachment: fixed;
}

@media ( max-width : 700px) {
	.body-container {
		background-size: cover;
	}
}

@media ( max-width : 998px) {
	.countdown-items {
		font-size: 0.8em;
	}
}

@media ( max-width : 400px) {
	.countdown-items {
		font-size: 0.75em;
	}
}

.countdown-item {
	display: inline-block;
	width: 12.5em;
	height: 12.5em;
	margin: 0.5em;
}

.piechart-legends ul {
	list-style: none;
	margin-left: 1.5rem;
	padding-left: 0;
}

.piechart-legends li {
	margin-bottom: 0.25rem;
	white-space: nowrap;
}

.piechart-legends span {
	display: inline-block;
	vertical-align: middle;
	border-radius: 1rem;
	width: 0.5rem;
	height: 0.5rem;
	margin-right: 0.5rem;
}

.piechart-legends-info li {
	margin-bottom: 0.25rem;
	white-space: nowrap;
}

.rtl .piechart-legends ul {
	list-style: none;
	margin-left: 0;
	margin-right: 1.5rem;
	padding-right: 0;
}

.rtl .piechart-legends span {
	margin-left: 0.5rem;
	margin-right: 0;
}
/* some styling for piecharts legends */
.piechart-legends ul {
	list-style: none;
	margin-left: 1.5rem;
	padding-left: 0;
}

.piechart-legends li {
	margin-bottom: 0.25rem;
	white-space: nowrap;
}

.piechart-legends span {
	display: inline-block;
	vertical-align: middle;
	border-radius: 1rem;
	width: 0.5rem;
	height: 0.5rem;
	margin-right: 0.5rem;
}

.piechart-legends-info li {
	margin-bottom: 0.25rem;
	white-space: nowrap;
}

.rtl .piechart-legends ul {
	list-style: none;
	margin-left: 0;
	margin-right: 1.5rem;
	padding-right: 0;
}

.rtl .piechart-legends span {
	margin-left: 0.5rem;
	margin-right: 0;
}
/* some styling for piecharts legends */
.piechart-legends ul {
	list-style: none;
	margin-left: 1.5rem;
	padding-left: 0;
}

.piechart-legends li {
	margin-bottom: 0.25rem;
	white-space: nowrap;
}

.piechart-legends span {
	display: inline-block;
	vertical-align: middle;
	border-radius: 1rem;
	width: 0.5rem;
	height: 0.5rem;
	margin-right: 0.5rem;
}

.piechart-legends-info li {
	margin-bottom: 0.25rem;
	white-space: nowrap;
}

.rtl .piechart-legends ul {
	list-style: none;
	margin-left: 0;
	margin-right: 1.5rem;
	padding-right: 0;
}

.rtl .piechart-legends span {
	margin-left: 0.5rem;
	margin-right: 0;
}
/* some styling for piecharts legends */
.piechart-legends ul {
	list-style: none;
	margin-left: 1.5rem;
	padding-left: 0;
}

.piechart-legends li {
	margin-bottom: 0.25rem;
	white-space: nowrap;
}

.piechart-legends span {
	display: inline-block;
	vertical-align: middle;
	border-radius: 1rem;
	width: 0.5rem;
	height: 0.5rem;
	margin-right: 0.5rem;
}

.piechart-legends-info li {
	margin-bottom: 0.25rem;
	white-space: nowrap;
}

.rtl .piechart-legends ul {
	list-style: none;
	margin-left: 0;
	margin-right: 1.5rem;
	padding-right: 0;
}

.rtl .piechart-legends span {
	margin-left: 0.5rem;
	margin-right: 0;
}

.conversation .media-body {
	-ms-flex: 0 0 auto;
	flex: 0 0 auto;
}

.conversation:nth-child(2n) .media>.d-flex {
	-ms-flex-direction: row-reverse;
	flex-direction: row-reverse;
}

.conversation:nth-child(2n) .media-body {
	border-radius: 1rem 1rem 2px 1rem;
}

.conversation:nth-child(2n+1) .media-body {
	border-radius: 1rem 1rem 1rem 2px;
}

.conversation:nth-child(2n) .avatar {
	margin-left: 0.5rem;
}

.conversation:nth-child(2n+1) .avatar {
	margin-right: 0.5rem;
}

.rtl .conversation:nth-child(2n) .media-body {
	border-radius: 1rem 1rem 1rem 2px;
}

.rtl .conversation:nth-child(2n+1) .media-body {
	border-radius: 1rem 1rem 2px 1rem;
}

.rtl .conversation:nth-child(2n) .avatar {
	margin-right: 0.5rem;
}

.rtl .conversation:nth-child(2n+1) .avatar {
	margin-left: 0.5rem;
}

.piechart-legends ul {
	list-style: none;
	margin-left: 0;
	padding-left: 0;
	font-size: 0.925rem;
}

.piechart-legends ul li {
	margin-bottom: 0.25rem;
	white-space: nowrap;
}

.piechart-legends span {
	display: inline-block;
	vertical-align: middle;
	border-radius: 1px;
	width: 0.625rem;
	height: 0.625rem;
	margin-right: 0.5rem;
}

.rtl .piechart-legends ul {
	margin-right: 0;
	padding-right: 0;
}

.rtl .piechart-legends span {
	margin-right: 0;
	margin-left: 0.5rem;
}

.bootstrap-wysiwyg-toolbar>.btn-group {
	background-color: #fff;
	margin-right: 0.25rem;
	border-radius: 0.25rem;
}

@media ( min-width : 1200px) {
	.navbar-white .navbar-inner {
		border-bottom-color: #e6eaed !important;
	}
}

#infobox-row {
	box-shadow: 0 0 0.25rem rgba(0, 0, 0, 0.075);
	border-radius: 0.25rem;
	overflow: hidden;
}

@media ( max-width : 991.98px) {
	#infobox-row {
		box-shadow: none;
		border-radius: 0;
	}
	#infobox-row div[role=button] {
		border-width: 0 !important;
		border-radius: 0.25rem;
		box-shadow: 0 0 0.25rem rgba(0, 0, 0, 0.075);
	}
}
/* some styling for new icons, to look good on sidebar */
.sidebar .nav>.nav-item>.nav-link>.nav-icon.material-icons, .sidebar .nav>.nav-item>.nav-link>.nav-icon.material-icons-outlined,
	.sidebar .nav>.nav-item>.nav-link>.nav-icon.jam, .sidebar .nav>.nav-item>.nav-link>.nav-icon.eva
	{
	font-size: 1.5rem;
	line-height: inherit;
	width: 1.375rem;
	margin-left: 0.125rem;
}

/* material icons carets */
.sidebar .caret.material-icons, .sidebar .caret.material-icons-outlined
	{
	font-size: 1.25rem;
	margin-right: 0.575rem;
}

.sidebar .submenu .caret.material-icons, .sidebar .submenu .caret.material-icons-outlined
	{
	margin-right: 1.125rem;
}

/* make jam icon carets smaller */
.sidebar .caret.jam {
	font-size: 1.125rem;
	margin-right: 0.625rem;
}

.sidebar .submenu .caret.jam {
	margin-right: 1.175rem;
}

/* make eva icon carets larger */
.sidebar .caret.eva {
	font-size: 1.5rem;
	margin-right: 0.5rem;
}

.sidebar .submenu .caret.eva {
	margin-right: 1rem;
}

html {
	--navbar-height: 5rem;
	--navbar-sm-height: 3.75rem;
}

/**
 See @page-script.js 
*/
#scroll-down {
	position: absolute;
	top: 60vh;
}

#scroll-up {
	position: absolute;
	top: 0;
}

/* when we scroll down a little bit, navbar becomes fixed (+compact) and appears from top */
@
keyframes navbarAppearIn { 0% {
	transform: translateY(-100%);
}

100










%
{
transform










:










none








;
}
}

/* if our ace.css is compiled using sticky: true, then we should make it position: fixed again to fix a few glitches */
.navbar-fixed {
	position: relative;
	animation: none;
	transition: none;
}

.navbar-compact {
	height: 5rem;
}

.navbar-fixed .navbar-inner {
	position: fixed;
	top: 0;
}

.navbar-compact .navbar-inner {
	height: 4rem;
	animation: navbarAppearIn 300ms;
}

@media ( prefers-reduced-motion : reduce) {
	.navbar .navbar-inner {
		transition: none !important;
		animation: none !important;
	}
}

#navbar-dark.navbar-compact .navbar-inner {
	border-bottom: 1px solid rgba(0, 0, 0, 0.075);
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.125);
}

/* the shadow for the 3 feature items ... speed, flexibility, etc */
.shadow-1 {
	box-shadow: 0 0.125rem 0.375rem rgba(0, 0, 0, 0.1);
}

/* the triangle in light/white version */
.shape-triangle {
	width: 0;
	height: 0;
	border-style: solid;
	border-width: 0 12px 20px 12px;
	border-top-color: transparent !important;
	border-left-color: transparent !important;
	border-right-color: transparent !important;
}

html {
	--navbar-height: 5rem;
	--navbar-sm-height: 3.5rem;
}

/**
 See @page-script.js 
*/
#scroll-down {
	top: 25vh;
}

/* if our ace.css is compiled using sticky: true, then we should make it position: fixed again to fix a few glitches */
.navbar-fixed {
	position: relative;
}

.navbar-compact {
	height: 5rem;
}

.navbar-inner {
	transition: height 250ms;
}

.navbar-compact .navbar-inner {
	height: 3.5rem;
}

.navbar-fixed .navbar-inner {
	position: fixed;
	top: 0;
}

@media ( prefers-reduced-motion : reduce) {
	.navbar-inner {
		transition: none;
	}
}

.page-intro {
	background-repeat: no-repeat;
	background-position: center 1.5rem;
	background-size: 100% auto;
	background-attachment: fixed;
}

/* change background position and size according to window size */
@media ( max-width : 1800px) {
	.page-intro {
		background-size: auto 480px;
	}
}

@media ( max-width : 1199.98px) {
	.page-intro {
		background-size: auto 420px;
	}
}

@media ( max-width : 700px) {
	.page-intro {
		background-position: -50vw 1rem;
	}
}

@media ( max-width : 450px) {
	.page-intro {
		background-position: -100vw 1rem;
	}
}

.footer {
	background: url('assets/image/landing/travel-footer.jpg') no-repeat
		center;
	background-size: auto;
	background-attachment: fixed;
	background-position: bottom;
}

@media ( max-width : 1000px) {
	.footer {
		background-size: auto 80%;
	}
}

@media ( max-width : 450px) {
	.footer {
		background-position: 60% 60%;
		background-size: auto 100%;
	}
}

.message-user {
	width: 8rem;
	white-space: nowrap;
	overflow: hidden;
}

.message-time {
	width: 4.5rem;
}

.message-item {
	flex: 0 0 auto !important;
}

.message-item:hover {
	z-index: 1;
	box-shadow: 0 1px 2px 1px rgba(0, 0, 0, 0.1);
}

.body-container {
	background-image: linear-gradient(#6baace, #264783);
	background-attachment: fixed;
	background-repeat: no-repeat;
}

.carousel-item>div {
	height: 100%;
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center;
}

/* these rules are used to make sure in mobile devices, tab panes are not all the same height (for example 'forgot' pane is not as tall as 'signup' pane) */
@media ( max-width : 1199.98px) {
	.tab-sliding .tab-pane:not(.active) {
		max-height: 0 !important;
	}
	.tab-sliding .tab-pane.active {
		min-height: 80vh;
		max-height: none !important;
	}
}

@media ( max-width : 767.98px) {
	.sticky-nav .nav-tabs {
		transition: margin 200ms;
	}
	.sticky-nav.is-stuck .nav-tabs {
		border-radius: 0 !important;
		border-width: 0 0 1px 0 !important;
		border-bottom-color: rgba(0, 0, 0, 0.12) !important;
		margin-left: -1rem;
		margin-right: -1rem;
		box-shadow: 0 1px 5px 2px rgba(0, 0, 0, 0.09);
	}
	.sticky-nav>.position-tr.w-100 {
		z-index: 1;
	}

	/* hide the blue border when nav gets fixed */
	.sticky-nav.is-stuck>.position-tr.w-100 {
		display: none;
	}
}

.detail-row+tr>td {
	border-top-width: 0;
}

.table-detail {
	border-bottom: 1px solid;
	border-color: inherit;
}

.body-container {
	/* so that sticky thead works */
	overflow: visible;
}

thead.is-stuck>tr>th {
	border-bottom: 1px solid #e8e8e8 !important;
}
</style>

</head>

<body>
	<div class="body-container">
		<nav class="navbar navbar-expand-lg navbar-fixed navbar-blue">
			<div class="navbar-inner">

				<div class="navbar-intro justify-content-xl-between">

					<button type="button"
						class="btn btn-burger burger-arrowed static collapsed ml-2 d-flex d-xl-none"
						data-toggle-mobile="sidebar" data-target="#sidebar"
						aria-controls="sidebar" aria-expanded="false"
						aria-label="Toggle sidebar">
						<span class="bars"></span>
					</button>
					<!-- mobile sidebar toggler button -->

					<a class="navbar-brand text-white" href="#"> <i
						class="fa fa-leaf"></i> <span>HRMS</span> <span>App</span>
					</a>
					<!-- /.navbar-brand -->

					<button type="button" class="btn btn-burger mr-2 d-none d-xl-flex"
						data-toggle="sidebar" data-target="#sidebar"
						aria-controls="sidebar" aria-expanded="true"
						aria-label="Toggle sidebar">
						<span class="bars"></span>
					</button>
					<!-- sidebar toggler button -->

				</div>
				<!-- /.navbar-intro -->


				<div class="navbar-content">



					<div class="navbar-menu collapse navbar-collapse navbar-backdrop"
						id="navbarMenu"></div>
					<!-- /#navbarMenu -->


				</div>
				<!-- /.navbar-inner -->
		</nav>
		<div class="main-container bgc-white">

			<div id="sidebar"
				class="sidebar sidebar-fixed expandable sidebar-light">
				<div class="sidebar-inner">

					<div class="ace-scroll flex-grow-1" data-ace-scroll="{}">

						<ul class="nav has-active-border active-on-right">

							<li class="nav-item active"><a href="/hrms/role?action=role" class="nav-link">
									<i class="nav-icon fa fa-tachometer-alt"></i> <span
									class="nav-text fadeable"> <span>Role</span>
								</span>


							</a> <b class="sub-arrow"></b></li>


							<li class="nav-item"><a href="/hrms/employee?action=getAllEmployee"
								class="nav-link dropdown-toggle collapsed"> <i
									class="nav-icon fa fa-cube"></i> <span
									class="nav-text fadeable"> <span>Employee</span>
								</span></li>

						</ul>

					</div>

				</div>
			</div>