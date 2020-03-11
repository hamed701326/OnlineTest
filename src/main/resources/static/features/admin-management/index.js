function loadPage(page) {
    $('#app-content .app-content-load')
        .load('features/admin-management/list-users-by-admin/'+page)
}