<!doctype html>
<#include "common/header.ftl">
<body>
<header>
    <nav class="navbar bg-dark navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/dashboard">
                <img src="/img/back.svg" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
                Requests
            </a>
        </div>
    </nav>
</header>
<main>
    <div id="request-list" class="list-group list-group-flush border-bottom scrollarea">
    </div>

    <div id="request-detail" class="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Detail #(<span id="detail-id"></span>)</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="col-10 mb-1 small">
                        <span id="detail-method" class="badge text-bg-primary"></span>
                        <span id="detail-url"></span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</main>
<#include "common/script.ftl">

<script>
    var data = ${data};
</script>
<script src="/js/byte.js"></script>
<script src="/js/requests.js"></script>
</body>

</html>