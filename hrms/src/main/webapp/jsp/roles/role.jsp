<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

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
                <input type="checkbox" />
              </th>

              <th class="border-0 bgc-white bgc-h-yellow-l3 shadow-sm">
                Name
              </th>

              <th class="border-0 bgc-white bgc-h-yellow-l3 shadow-sm">
                Office
              </th>

              <th class="border-0 bgc-white bgc-h-yellow-l3 shadow-sm">
                Age
              </th>

              <th class="border-0 bgc-white bgc-h-yellow-l3 shadow-sm">
                Start date
              </th>

              <th class="border-0 bgc-white bgc-h-yellow-l3 shadow-sm">
                Salary
              </th>

              <th class="border-0 bgc-white shadow-sm w-2">
                <!-- the TD will have edit icon -->
              </th>
            </tr>
          </thead>

          <tbody class="pos-rel">
            <tr class="d-style bgc-h-default-l4">
              <td class="td-toggle-details pos-rel">
                <!-- this empty table cell will show the `+` sign which toggles the hidden cells in responsive (collapsed) mode -->

                <div class="position-lc h-95 ml-1px border-l-3 brc-purple-m1">
                  <!-- this decorative highlight border will be shown only when table is collapsed (responsive) -->
                </div>
              </td>

              <td class="pl-3 pl-md-4 align-middle pos-rel">
                <input type="checkbox" />
                <div class="d-n-collapsed position-lc h-95 ml-1px border-l-3 brc-purple-m1">
                  <!-- this decorative highlight border will be shown only when table is in full mode (not collapsed >> .d-n-collapsed) -->
                </div>
              </td>

              <td>
                <span class="text-105">
                    Tigerd Nixon
                </span>
                <div class="text-95 text-secondary-d1">
                  System Architect
                </div>
              </td>

              <td class="text-grey">
                Edinburgh
                <div><span class='badge bgc-orange-d1 text-white badge-sm'>On Vacation</span></div>
              </td>

              <td class="text-600 text-grey-d1">
                61
              </td>

              <td class="text-grey">
                2011/04/25
              </td>

              <td>
                <i class="fa fa-arrow-down text-orange-d1"></i>
                $320
              </td>

              <td class="align-middle">
                <span class="d-none d-lg-inline">
                    <a data-rel="tooltip" data-action="edit" title="Edit" href="#" class="v-hover">
                        <i class="fa fa-edit text-blue-m1 text-120"></i>
                    </a>
                </span>

                <span class="d-lg-none text-nowrap">
                    <a title="Edit" href="#" class="btn btn-outline-info shadow-sm px-4 btn-bgc-white">
                        <i class="fa fa-pencil-alt mx-1"></i>
                        <span class="ml-1 d-md-none">Edit</span>
                </a>
                </span>
              </td>
            </tr>
          </tbody>
        </table>

      </form>
    </div>
  </div>
</c:if>


<c:if test="${action == 'insertRole' || action == 'updateRole'}">


</c:if>

<jsp:include page="../../jsp/footer/footerNew.jsp" />
<script>
jQuery(function($) {
  var tableId = '#datatable'

  $.extend( true, $.fn.dataTable.defaults, {
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
        targets:   0
      },
      {
        orderable: false,
        className: null,
        targets:   1
      },
      {
        orderable: false,
        className: null,
        targets:   -1
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
  $('.table-tools-col' )
  .append( $_table.buttons().container() )
  // move searchbox into table header
  .find('.dataTables_filter').appendTo('.page-tools').find('input').addClass('pl-45 radius-round').removeClass('form-control-sm')
  // and add a "+" button
  .end().append('<button id="addBtn" data-rel="tooltip" type="button" class="btn radius-round btn-outline-primary border-2 btn-sm ml-2" title="Add New"><i class="fa fa-plus"></i></button>')

  // helper methods to add/remove bgc-h-* class when selecting/deselecting rows
  var _highlightSelectedRow = function(row) {
    row.querySelector('input[type=checkbox]').checked = true
    row.classList.add('bgc-success-l3')
    row.classList.remove('bgc-h-default-l4')
  }
  var _unhighlightDeselectedRow = function(row) {
    row.querySelector('input[type=checkbox]').checked = false
    row.classList.remove('bgc-success-l3')
    row.classList.add('bgc-h-default-l4')
  }

  // listen to select/deselect event to highlight rows
  $_table
  .on('select', function (e, dt, type, index) {
    if ( type == 'row' ) {
      var row = $_table.row( index ).node()
      _highlightSelectedRow(row)
    }
  })
  .on('deselect', function (e, dt, type, index) {
    if ( type == 'row' ) {
      var row = $_table.row( index ).node()
      _unhighlightDeselectedRow(row)
    }
  })

  // when clicking the checkbox in table header, select/deselect all rows
  $(tableId)
  .on('click', 'th input[type=checkbox]', function () {
    if(this.checked) {
      $_table.rows().select().every(function() {
        _highlightSelectedRow(this.node())
      });
    }
    else {
      $_table.rows().deselect().every(function() {
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
    var toggleTH_highlight = function(th) {
      th.classList.toggle('bgc-yellow-l2')
      th.classList.toggle('bgc-h-yellow-l3')
      th.classList.toggle('text-blue-d2')
    }

    $(tableId)
    .on('click', 'th:not(.sorting_disabled)', function () {
      if(previousTh != null) toggleTH_highlight(previousTh)// unhighlight previous TH
      toggleTH_highlight(this)
      previousTh = this
    })

    // don't select row when clicking on the edit icon
    $('a[data-action=edit').on('click', function(e) {
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
    $('.dataTables_scrollBody').aceScroll({color: "grey"})


    //enable tooltips
    setTimeout(function() {
      $('.dt-buttons button')
      .each(function() {
        var div = $(this).find('span').first()
        if(div.length == 1) $(this).tooltip({container: 'body', title: div.parent().text()})
        else $(this).tooltip({container: 'body', title: $(this).text()})
      })
      $('[data-rel=tooltip').tooltip({container: 'body'})
    }, 0)

    $('#addBtn').on('click',()=>{
        window.location.href="/hrms/role?action=insertRole"
    });

})
</script>

  </body>

</html>