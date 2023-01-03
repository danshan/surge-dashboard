<!doctype html>
<#include "common/header.ftl">

<body>
<header>
    <nav class="navbar bg-dark navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Navbar</a>
        </div>
    </nav>
</header>
<main>
    <div id="request-list" class="list-group list-group-flush border-bottom scrollarea">
    </div>
</main>
<#include "common/script.ftl">
<script src="/byte.js"></script>
<script src="/requests.js"></script>
</body>

</html>