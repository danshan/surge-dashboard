<!doctype html>
<#include "common/header.ftl">

<body>
<main>
    <div id="chart-title" class="bg-light text-center">Global</div>
    <div class="bg-light">
        <canvas id="traffic-chart"></canvas>
    </div>

    <div class="bg-light">
        <div class="row" onclick="showGlobalSpeed()">
            <div class="col-sm-4 col-4 text-center">
                上传<br>
                <strong id="upload-speed">--</strong>
            </div>
            <div class="col-sm-4 col-4 text-center">
                下载<br>
                <strong id="download-speed">--</strong>
            </div>
            <div class="col-sm-4 col-4 text-center">
                总计<br>
                <strong id="total-data">--</strong>
            </div>
        </div>
    </div>

    <dev>
        <table id="device-table" class="table table-striped"></table>
    </dev>
</main>


<#include "common/script.ftl">
<script src="/byte.js"></script>
<script src="/dashboard.js"></script>
</body>

</html>