(function () {
    let physicalAddress = data ? data.physicalAddress : undefined;

    let requestList = $('#request-list');

    let refreshData = function () {
        $.ajax({
            url: '/api/surge/requests',
            success: function (requests) {
                let html = '';
                for (req of requests) {
                    if (physicalAddress && req.physicalAddress !== physicalAddress) {
                        continue;
                    }
                    html += `
                <a href="#" class="list-group-item list-group-item-action py-3 lh-sm" data-bs-toggle="modal" data-bs-target="#request-detail" onclick="showRequestDetail(req)">
                    <div class="d-flex w-100 align-items-center justify-content-between">
                        <span class="mb-1">${req.url}</span>
                    </div>
                    <div class="col-10 mb-1 small">
                        <span class="badge text-bg-primary">${req.method}</span>
                        #${req.id} - ${req.startDate} - ${req.policyName} - ${bytes(req.inBytes, {decimalPlaces: 0})} - ${req.status}
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