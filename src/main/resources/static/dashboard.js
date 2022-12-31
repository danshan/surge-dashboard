const ctx = document.getElementById('traffic-chart');

let chartTarget = 'global';
let selectedDevice = undefined;
let deviceList = [];

const CHART_SIZE = 60;
const getInitialData = function () {
    const result = [];
    for (let i = 0; i < CHART_SIZE; i++) {
        result.push(0);
    }
    return result;
};

const labels = [];
for (let i = 0; i < CHART_SIZE; ++i) {
    labels.push(i.toString());
}

let globalUpload = getInitialData();
let globalDownload = getInitialData();
let deviceUpload = getInitialData();
let deviceDownload = getInitialData();

let data = {
    labels: labels,
    datasets: [
        {
            borderColor: '#27c8ae',
            borderWidth: 2,
            lineTension: 0.3,
            pointRadius: 0,
            backgroundColor: 'rgba(123, 166, 220, .2)',
            fill: true,
            label: 'Upload',
            data: globalUpload
        },
        {
            borderColor: '#f3a956',
            borderWidth: 2,
            lineTension: 0.3,
            pointRadius: 0,
            backgroundColor: 'rgba(243, 169, 86, .2)',
            fill: true,
            label: 'Download',
            data: globalDownload
        }
    ]
};

const trafficChart = new Chart(ctx, {
    type: 'line',
    data: data,
    options: {
        responsive: true,
        maintainAspectRatio: false,
        animation: {
            duration: 200,
            resize: {
                duration: 0,
            },
            active: {
                duration: 0,
            }
        },
        interaction: {
            mode: 'nearest',
            intersect: true,
        },
        scales: {
            x: {
                display: false,
                grid: {
                    display: false,
                    drawTicks: false,
                },
            },
            y: {
                display: true,
                grid: {
                    display: true,
                    color: '#c2c2c2',
                    tickBorderDash: [3, 6],
                    drawBorder: false,
                    drawTicks: false,
                },
                ticks: {
                    callback: function (value) {
                        return bytes(value, {decimalPlaces: 0}) + '/s ';
                    },
                    beginAtZero: true,
                    maxTicksLimit: 4,
                },
            },
        },
        plugins: {
            legend: {
                display: true,
                position: 'bottom',
                labels: {
                    color: '#ccc',
                    boxWidth: 20,
                },
            },
            tooltip: {
                enabled: false,
            },
            title: {
                display: false,
            },
        }
    },
});

const updateGlobalChart = function (speed) {
    globalUpload.push(speed.outCurrentSpeed);
    if (globalUpload.length > CHART_SIZE) {
        globalUpload = globalUpload.slice(-CHART_SIZE);
    }

    globalDownload.push(speed.inCurrentSpeed);
    if (globalDownload.length > CHART_SIZE) {
        globalDownload = globalDownload.slice(-CHART_SIZE);
    }

    if (chartTarget === "global") {
        for (let dataset of data.datasets) {
            if (dataset.label === 'Upload') {
                dataset.data = globalUpload;
            } else {
                dataset.data = globalDownload;
            }
        }
        trafficChart.update();
    }
}

const updateDeviceChart = function() {
    const selected = deviceList.filter(device => device.physicalAddress === selectedDevice)[0];

    deviceUpload.push(selected.currentOutSpeed);
    if (deviceUpload.length > CHART_SIZE) {
        deviceUpload = deviceUpload.slice(-CHART_SIZE);
    }

    deviceDownload.push(selected.currentInSpeed);
    if (deviceDownload.length > CHART_SIZE) {
        deviceDownload = deviceDownload.slice(-CHART_SIZE);
    }

    if (chartTarget === "device") {
        for (let dataset of data.datasets) {
            if (dataset.label === 'Upload') {
                dataset.data = deviceUpload;
            } else {
                dataset.data = deviceDownload;
            }
        }
        trafficChart.update();
    }
}

const updateGlobalSpeed = function (speed) {
    $('#upload-speed').html(bytes(speed.outCurrentSpeed, {decimalPlaces: 2}) + '/s ');
    $('#download-speed').html(bytes(speed.inCurrentSpeed, {decimalPlaces: 2}) + '/s ');
    $('#total-data').html(bytes((speed.in + speed.out), {decimalPlaces: 2}));
}


const getGlobalSpeed = function () {
    $.ajax({
        url: '/api/surge/speed',
        success: function (speed) {
            updateGlobalChart(speed);
            updateGlobalSpeed(speed);
        }
    });
};

const getDevices = function () {
    $.ajax({
        url: '/api/surge/devices',
        success: function (devices) {
            deviceList = devices;
            let html = '';
            $.each(devices, function (index, device) {
                html += `
                <tr onclick='showDeviceSpeed("${device.physicalAddress}")'>
                    <th scope="row">${index}</th>
                    <td>${device.name}</td>
                    <td>${device.sourceIP}</td>
                    <td>${device.physicalAddress}</td>
                    <td>${bytes(device.currentOutSpeed, {decimalPlaces: 2})}/s</td>
                    <td>${bytes(device.currentInSpeed, {decimalPlaces: 2})}/s</td>
                    <td>${bytes(device.totalBytes, {decimalPlaces: 0})}</td>
                </tr>
                `;
            });

            $("#device-list").html(html);
            if (chartTarget === 'device') {
                updateDeviceChart();
            }
        }
    });
}

const refreshData = function () {
    getGlobalSpeed();
    getDevices();
};

setInterval(refreshData, 1000);

const showGlobalSpeed = function () {
    if (chartTarget === 'global') {
        return;
    }
    chartTarget = 'global';
    $("#chart-title").html("Global Speed")
};

const showDeviceSpeed = function (physicalAddress) {
    selectedDevice = physicalAddress;
    const selected = deviceList.filter(device => device.physicalAddress === selectedDevice)[0];
    chartTarget = 'device';
    $("#chart-title").html(`${selected.name} - ${selected.sourceIP}`);

    deviceUpload = getInitialData();
    deviceDownload = getInitialData();
};
