<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <%@ taglib prefix="c" uri="jakarta.tags.core" %>

    <jsp:include page="../../jsp/header/headerNew.jsp" />

    <c:if test="${action == 'getAllRoleData'}">

      <div class="page-content container container-plus">
        <div class="page-header mb-2 pb-2 flex-column flex-sm-row align-items-start align-items-sm-center py-25 px-1">
          <h1 class="page-title text-primary-d2 text-140">
            Role Master
          </h1>

          <div class="page-tools mt-3 mt-sm-0 mb-sm-n1">
            <!-- dataTables search box will be inserted here dynamically -->
          </div>
        </div>

        <div class="card bcard h-auto">
          <form autocomplete="off" class="border-t-3 brc-blue-m2">

            <table id="datatable" class="d-style w-100 table text-dark-m1 text-95 border-y-1 brc-black-tp11 collapsed">
              <!-- add `collapsed` by default ... it will be removed by default -->
              <!-- thead with .sticky-nav -->
              <thead class="sticky-nav text-secondary-m1 text-uppercase text-85">
                <tr>
                  <th class="td-toggle-details border-0 bgc-white shadow-sm">
                  </th>

                  <th class="border-0 bgc-white pl-3 pl-md-4 shadow-sm">
                    No.
                  </th>

                  <th class="border-0 bgc-white bgc-h-yellow-l3 shadow-sm">
                    Role Code
                  </th>

                  <th class="border-0 bgc-white bgc-h-yellow-l3 shadow-sm">
                    Role Name
                  </th>

                  <th class="border-0 bgc-white bgc-h-yellow-l3 shadow-sm">
                    Role Level
                  </th>

                  <th class="border-0 bgc-white bgc-h-yellow-l3 shadow-sm">
                    Reporting Role
                  </th>

                  <th class="border-0 bgc-white shadow-sm w-8">
                    Action
                  </th>
                </tr>
              </thead>

              <tbody class="pos-rel">
                <c:forEach items="${roleList}" var="item" varStatus="loop">

                  <tr class="d-style bgc-h-default-l4">
                    <td class="td-toggle-details pos-rel">
                      <!-- this empty table cell will show the `+` sign which toggles the hidden cells in responsive (collapsed) mode -->

                      <div class="position-lc h-95 ml-1px border-l-3 brc-purple-m1">
                        <!-- this decorative highlight border will be shown only when table is collapsed (responsive) -->
                      </div>
                    </td>

                    <td class="pl-3 pl-md-4 align-middle pos-rel">
                      ${loop.index +1}
                    </td>

                    <td class="text-105 align-middle">
                      ${item.roleCode }
                    </td>

                    <td class="text-105 align-middle">
                      ${item.roleName }
                    </td>

                    <td class="text-105 align-middle">
                      ${item.roleLevel }
                    </td>

                    <td class="text-105 align-middle">
                      ${item.reportingRole }
                    </td>

                    <td class="align-middle">
                      <span class="d-none d-lg-inline">
                        <button id="editBtn-${item.roleCode }" class="btn btn-outline-light btn-h-light-blue btn-a-light-blue border-b-2 text-600 px-3 mb-1">
                          <i class="fa fa-pencil-alt text-110 text-blue-d2 mr-1"></i>
                        </button>
                        <button id="deleteBtn-${item.roleCode }" class="btn btn-outline-light btn-h-light-danger btn-a-light-danger border-b-2 text-600 px-3 mb-1">
                          <i class="fa fa-trash-alt text-110 text-danger-d2 mr-1"></i>
                        </button>
                      </span>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>

          </form>
        </div>
      </div>
    </c:if>


    <c:if test="${action == 'insertRole' || action == 'updateRole'}">
      <div class="page-content container container-plus">
        <form autocomplete="off" class="mt-475" id="roleForm">
          <div class="form-group row">
            <div class="col-lg-6">
              <div class="card h-100">
                <div class="card-header">
                  <span class="card-title text-125">
                    Role Form
                  </span>
                </div>

                <div class="card-body">

                  <div class="form-group row">
                    <div class="col-sm-9">
                      <input type="hidden" class="form-control" id="roleCode" name="roleCode" value="${selectedRole.roleCode}"/>
                    </div>
                  </div>

                  <div class="form-group row">
                    <div class="col-sm-3 col-form-label text-sm-left pr-0">
                      <label for="roleName" class="mb-0">
                        Role Name
                      </label>
                    </div>
                    <div class="col-sm-9">
                      <input type="text" class="form-control" id="roleName" name="roleName" value="${selectedRole.roleName}" required/>
                    </div>
                  </div>

                  <div class="form-group row">
                    <div class="col-sm-3 col-form-label text-sm-left pr-0">
                      <label for="roleLevel" class="mb-0">
                        Role Level
                      </label>
                    </div>
                    <div class="col-sm-9">
                      <input type="number" class="form-control" min=0 max=999 id="roleLevel" name="roleLevel" value="${selectedRole.roleLevel}" required/>
                    </div>
                  </div>

                  <div class="form-group row">
                    <div class="col-sm-3 col-form-label text-sm-left pr-0">
                      <label for="idReportingRole" class="mb-0">
                        Reporting Role
                      </label>
                    </div>
                    <div class="col-sm-9">
                      <select
                        class="ace-select text-dark-m1 bgc-default-l5 bgc-h-warning-l3 brc-default-m3 brc-h-warning-m1"
                        id="idReportingRole">
                        <c:if test="${not empty roleList}">
                            <c:forEach items="${roleList}" var="item" varStatus="loop">
                                <option value='${item.roleCode}' <c:if test="${item.roleCode == selectedRole.roleReportingTo}">selected</c:if>>${item.roleName}</option>
                            </c:forEach>
                        </c:if>
                      </select>
                    </div>
                  </div>
                  <div class="form-group text-center">
                    <button class="btn btn-info btn-bold px-4 text-sm-right" id="submitBtn" type="button">
                      <i class="fa fa-check mr-1"></i>Submit
                    </button>
                    <button class="btn btn-outline-lightgrey btn-bgc-white btn-bold ml-2 px-4" type="reset">
                      <i class="fa fa-undo mr-1"></i>Reset
                    </button>
                  </div>

                </div>
              </div><!-- /.card -->
            </div><!-- /.col -->
          </div>
        </form>
      </div>

    </c:if>

    <jsp:include page="../../jsp/footer/footerNew.jsp" />

    <script>
      jQuery(function ($) {
        var tableId = '#datatable'

        $.extend(true, $.fn.dataTable.defaults, {
          dom:
            "<'row'<'col-12 col-sm-6'l><'col-12 col-sm-6 text-right table-tools-col'f>>" +
            "<'row'<'col-12'tr>>" +
            "<'row'<'col-12 col-md-5'i><'col-12 col-md-7'p>>",
          renderer: 'bootstrap'
        })

        var $_table = $(tableId).DataTable({
          responsive: true,

          colReorder: {
            //disable column reordering for first and last columns
            fixedColumnsLeft: 1,
            fixedColumnsRight: 1
          },

          classes: {
            sLength: "dataTables_length text-left w-auto",
          },


          // first and last column are not sortable
          columnDefs: [
            {
              orderable: false,
              className: null,
              targets: 0
            },
            {
              orderable: false,
              className: null,
              targets: 1
            },
            {
              orderable: false,
              className: null,
              targets: -1
            },
            {
              responsivePriority: 1,
              targets: 2
            }
          ],


          // multiple row selection
          select: {
            style: 'multis'
          },

          // no specific initial ordering
          order: [],

          language: {
            search: '<i class="fa fa-search pos-abs mt-2 pt-3px ml-25 text-blue-m2"></i>',
            searchPlaceholder: " Search Roles..."
          }
        })

        // specify position of table buttons
        $('.table-tools-col')
          .append($_table.buttons().container())
          // move searchbox into table header
          .find('.dataTables_filter').appendTo('.page-tools').find('input').addClass('pl-45 radius-round').removeClass('form-control-sm')
          // and add a "+" button
          .end().append('<button id="addBtn" data-rel="tooltip" type="button" class="btn radius-round btn-outline-primary border-2 btn-sm ml-2" title="Add New"><i class="fa fa-plus"></i></button>')

        // helper methods to add/remove bgc-h-* class when selecting/deselecting rows
        var _highlightSelectedRow = function (row) {
          row.querySelector('input[type=checkbox]').checked = true
          row.classList.add('bgc-success-l3')
          row.classList.remove('bgc-h-default-l4')
        }
        var _unhighlightDeselectedRow = function (row) {
          row.querySelector('input[type=checkbox]').checked = false
          row.classList.remove('bgc-success-l3')
          row.classList.add('bgc-h-default-l4')
        }

        // listen to select/deselect event to highlight rows
        $_table
          .on('select', function (e, dt, type, index) {
            if (type == 'row') {
              var row = $_table.row(index).node()
              _highlightSelectedRow(row)
            }
          })
          .on('deselect', function (e, dt, type, index) {
            if (type == 'row') {
              var row = $_table.row(index).node()
              _unhighlightDeselectedRow(row)
            }
          })

        // when clicking the checkbox in table header, select/deselect all rows
        $(tableId)
          .on('click', 'th input[type=checkbox]', function () {
            if (this.checked) {
              $_table.rows().select().every(function () {
                _highlightSelectedRow(this.node())
              });
            }
            else {
              $_table.rows().deselect().every(function () {
                _unhighlightDeselectedRow(this.node())
              })
            }
          })

        // don't select row if we click on the "show details" `plus` sign (td)
        $(tableId).on('click', 'td.dtr-control', function (e) {
          e.stopPropagation()
        })


        // add/remove bgc-h-* class to TH when soring columns
        var previousTh = null
        var toggleTH_highlight = function (th) {
          th.classList.toggle('bgc-yellow-l2')
          th.classList.toggle('bgc-h-yellow-l3')
          th.classList.toggle('text-blue-d2')
        }

        $(tableId)
          .on('click', 'th:not(.sorting_disabled)', function () {
            if (previousTh != null) toggleTH_highlight(previousTh)// unhighlight previous TH
            toggleTH_highlight(this)
            previousTh = this
          })

        // don't select row when clicking on the edit icon
        $('a[data-action=edit]').on('click', function (e) {
          e.preventDefault()
          e.stopPropagation()// don't select
        })

        // add a dark border
        $('.dataTables_wrapper')
          .addClass('border-b-1 border-x-1 brc-default-l2')

          // highlight DataTable header
          // also already done in CSS, but you can use custom colors here
          .find('.row:first-of-type')
          .addClass('border-b-1 brc-default-l3 bgc-blue-l4')
          .next().addClass('mt-n1px')// move the next `.row` up by 1px to go below header's border

        // highlight DataTable footer
        $('.dataTables_wrapper')
          .find('.row:last-of-type')
          .addClass('border-t-1 brc-default-l3 mt-n1px bgc-default-l4')


        // if the table has scrollbars, add ace styling to it
        $('.dataTables_scrollBody').aceScroll({ color: "grey" })


        //enable tooltips
        setTimeout(function () {
          $('.dt-buttons button')
            .each(function () {
              var div = $(this).find('span').first()
              if (div.length == 1) $(this).tooltip({ container: 'body', title: div.parent().text() })
              else $(this).tooltip({ container: 'body', title: $(this).text() })
            })
          $('[data-rel=tooltip]').tooltip({ container: 'body' })
        }, 0)

        $('#addBtn').on('click', () => {
          window.location.href = "/hrms/role?action=insertRole"
        });
        
        $('#submitBtn').on('click', (event) => {
          event.preventDefault();
          let form = $('#roleForm')[0];
          if (form.checkValidity() === false) {
            form.reportValidity()
            return
          }
          var url = "hrms/role?action=insertRole";
          <c:if test="${action == 'updateRole'}">
            url = "hrms/role?action=updateRole";
          </c:if>
          let roleCode = $('#roleCode').val();
          let roleName = $('#roleName').val();
          let roleLevel = $('#roleLevel').val();
          let reportingRole = $('#idReportingRole').val();
          var data = {
            "roleCode":roleCode,
            "roleName":roleName,
            "roleLevel":roleLevel,
            "reportingRole":reportingRole
          }
          $.ajax({
            type: "POST",
            url: url,
            data: data,
            cache: false,
            success: function(response) {
              var jsonData = JSON.parse(response);
              if (jsonData.isSuccess) {
                $.aceToaster.add({
                  placement: 'tr',
                  body: "<p class='p-3 mb-0 text-left text-white'><span class='text-125'>" + jsonData.message + "</span></p>",
                  width: 360,
                  delay: 4000,
                  close: true,
                  className: 'bgc-green-d2 shadow ',
                  bodyClass: 'border-0 p-0 text-dark-tp2',
                  headerClass: 'd-none',
                  progress: 'position-bl bgc-black-tp6 py-2px m-1px'
                });
                setTimeout(function() {
                  window.location.href = "hrms/role?action=getAllRoles";  // Adjust the redirect URL as needed
                }, 1500);
              } else {
                // Handle failure in success callback if isSuccess is false
                $.aceToaster.add({
                  placement: 'tr',
                  body: "<p class='p-3 mb-0 text-left text-white'><span class='text-125'>" + jsonData.message + "</span></p>",
                  width: 360,
                  delay: 4000,
                  close: true,
                  className: 'bgc-red-d2 shadow ',
                  bodyClass: 'border-0 p-0 text-dark-tp2',
                  headerClass: 'd-none',
                  progress: 'position-bl bgc-black-tp6 py-2px m-1px'
                });
              }
            },
            error: function(xhr) {
              // In case of HTTP errors, attempt to parse the error response
              try {
                var jsonData = JSON.parse(xhr.responseText);
                $.aceToaster.add({
                  placement: 'tr',
                  body: "<p class='p-3 mb-0 text-left text-white'><span class='text-125'>" + jsonData.message + "</span></p>",
                  width: 360,
                  delay: 4000,
                  close: true,
                  className: 'bgc-red-d2 shadow ',
                  bodyClass: 'border-0 p-0 text-dark-tp2',
                  headerClass: 'd-none',
                  progress: 'position-bl bgc-black-tp6 py-2px m-1px'
                });
              } catch (e) {
                // Fallback for non-JSON responses
                $.aceToaster.add({
                  placement: 'tr',
                  body: "<p class='p-3 mb-0 text-left text-white'><span class='text-125'>An unexpected error occurred.</span></p>",
                  width: 360,
                  delay: 4000,
                  close: true,
                  className: 'bgc-red-d2 shadow ',
                  bodyClass: 'border-0 p-0 text-dark-tp2',
                  headerClass: 'd-none',
                  progress: 'position-bl bgc-black-tp6 py-2px m-1px'
                });
              }
            },
          });
        });
        
// Edit button click handler
        $(document).on('click', '[id^=editBtn-]', function(e) {
            e.preventDefault(); // Prevent any default action
            e.stopPropagation(); // Stop the event from propagating to parent elements
            var id = $(this).attr('id').split('-')[1]; // Extract ID from the button's ID
            editRecord(id);
        });

    // Delete button click handler
    $(document).on('click', '[id^=deleteBtn-]', function(e) {
        e.preventDefault(); // Prevent any default action
        e.stopPropagation(); // Stop the event from propagating to parent elements
        var id = $(this).attr('id').split('-')[1]; // Extract ID from the button's ID
        deleteRecord(id);
    });

    // Edit function - Redirect to the edit page
    function editRecord(id) {
        // Redirect to edit page with the record ID
        window.location.href = 'hrms/role?action=updateRole&roleCode=' + id;
    }

    // Delete function - Confirm and redirect
    function deleteRecord(id) {
        // Confirm with the user
        var confirmation = confirm('Are you sure you want to delete this record?');

        // If confirmed, redirect to delete action
        if (confirmation) {
            window.location.href = 'hrms/role?action=deleteRole&roleCode=' + id;
        }
    }
        $(document).ready(function() {
          $('#roleName').on('input', function () {
            this.value = this.value.replace(/[^A-Za-z\s]/g, ''); // Allow only letters
          });
          $('#roleLevel').on('input', function () {
            this.value = this.value.replace(/[^0-9]/g, ''); // Allow only digits
          });
        });

      })
    </script>

    </body>

    </html>