(function () {
    let sourceIP = data ? data.sourceIP: undefined;
    let device = data ? data.device: undefined;

    let requestList = $('#request-list');

    let formatTime = function(req) {
        return moment(req.startDate * 1000).format("hh:mm:ss");
    }

    let formatMethod = function(req) {
        let method = req.method
        let status = req.status
        let theme = 'text-bg-primary'
        switch (status) {
            case 'Completed':
                break
            case 'Active':
                theme = 'text-bg-success'
                break
            default:
                theme = 'text-bg-danger'
                break
        }
        return `<span class="badge ${theme}">${method}</span>`
    }

    let refreshData = function () {
        $.ajax({
            url: '/api/surge/requests',
            success: function (requests) {
                let html = '';
                for (req of requests) {
                    if (sourceIP && req.sourceAddress !== sourceIP) {
                        continue;
                    }
                    html += `
                <a href="#" class="list-group-item list-group-item-action py-3 lh-sm" data-bs-toggle="modal" data-bs-target="#request-detail">
                    <div class="d-flex w-100 align-items-center justify-content-between">
                        <span class="mb-1">${req.url}</span>
                    </div>
                    <div class="col-10 mb-1 small">
                        ${formatMethod(req)}
                        #${req.id} - ${formatTime(req)} - ${req.policyName} - ${bytes(req.inBytes, {decimalPlaces: 0})} - ${req.status}
                    </div>
                </a>
                `;
                }

                requestList.html(html);
            }
        });
    };

    let showRequestDetail = function (req) {
        $("#detail-id").text(req.id);
        $("#detail-method").text(req.method);
        $("#detail-url").text(req.url);
    }

    setInterval(refreshData, 1000);
})();