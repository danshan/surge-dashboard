<!doctype html>
<#include "common/header.ftl">

<body>

<#include "common/navbar.ftl">
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

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Source IP</th>
            <th scope="col">MAC</th>
            <th scope="col">Upload</th>
            <th scope="col">Download</th>
            <th scope="col">Total</th>
        </tr>
        </thead>
        <tbody id="device-list">
        </tbody>
    </table>
</main>


<#include "common/script.ftl">
<script src="/byte.js"></script>
<script src="/dashboard.js"></script>
</body>

</html>