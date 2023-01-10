(function () {
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

    let chartValue = {
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
        data: chartValue,
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
            for (let dataset of chartValue.datasets) {
                if (dataset.label === 'Upload') {
                    dataset.data = globalUpload;
                } else {
                    dataset.data = globalDownload;
                }
            }
            trafficChart.update();
        }
    }

    const updateDeviceChart = function () {
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
            for (let dataset of globalData.datasets) {
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
                let tabledata = [];
                $.each(devices, function (index, device) {
                    tabledata.push({
                        id: index + 1,
                        name: device.name,
                        sourceIP: device.sourceIP,
                        physicalAddress: device.physicalAddress,
                        upload: bytes(device.currentOutSpeed, {decimalPlaces: 2}),
                        download: bytes(device.currentInSpeed, {decimalPlaces: 2}),
                        totalBytes: bytes(device.totalBytes, {decimalPlaces: 0})
                    });
                });

                $("#device-table").bootstrapTable('load', tabledata);

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

    const operateFormatter = function(value, row, index) {
        return [
            `<a class="like" href="/requests?sourceIP=${row.sourceIP}" title="Like">`,
            '<button type="button" class="btn btn-primary btn-sm">Requests</button>',
            '</a>  ',
        ].join('')
    }

    $("#device-table").bootstrapTable({
        data: [],
        columns: [
            {
                field: 'name',
                title: 'Device Name',
                sortable: true,
            },
            {
                field: 'sourceIP',
                title: 'Device IP',
                sortable: true,
            },
            {
                field: 'physicalAddress',
                title: 'MAC Address',
                sortable: true,
            },
            {
                field: 'upload',
                title: 'Up',
                sortable: true,
                searchable: false,
            },
            {
                field: 'download',
                title: 'Down',
                sortable: true,
                searchable: false,
            },
            {
                field: 'totalBytes',
                title: 'Total',
                sortable: true,
                searchable: false,
            },
            {
                field: 'operate',
                title: 'Ops',
                align: 'center',
                clickToSelect: false,
                formatter: operateFormatter
            }
        ],
        search: true,
        searchHighlight: true,
        showColumns: true,
        onClickRow: function (row, $element, field) {
            showDeviceSpeed(row.physicalAddress);
        },
    });

    setInterval(refreshData, 1000);
})();