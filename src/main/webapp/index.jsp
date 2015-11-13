<html>
<head>
<title>CQLSH++</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
.gf-div {
	overflow: hidden;
	padding-left: 10px;
	padding-top: 10px;
}

.gf-table-div {
	padding-left: 0px;
}

.tgt-even {
	background-color: #f4f3f3;
}

.gf-table {
	width: 100%;
}

.gf-table tbody {
	position: relative;
	display: block;
	overflow-y: auto;
	overflow-x: hidden;
	height: 87px;
	width: 100%;
}

.gft-content3 {
	position: relative;
	display: block;
	overflow-y: auto;
	overflow-x: hidden;
	width: 100%;
}

.gft-header {
	border-left: none;
	border-right: none;
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	background-color: #D1EDF6;
	border-bottom: 1px solid #91ABBD;
	border-top: 1px solid #91ABBD;
}

.gft-single-header td {
	border-right: none;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#runQuery')
								.click(
										function() {

											var txtArea = $('#queryInput')
													.val();
											var endPt = $('#endPoint').val();
											var jsonObj = {
												"query" : txtArea
											};
											$
													.ajax({
														url : endPt,
														type : 'POST',
														data : JSON
																.stringify(jsonObj),
														contentType : "application/json; charset=utf-8",
														success : function(data) {

															$("#dataHead")
																	.html('');
															$("#dataBody")
																	.html('');
															$("#errorMsg")
																	.html('');

															if (data.exception != null) {
																$("#errorMsg")
																		.html(
																				data.exception);
																return;
															}

															var headerFlag = true;
															var result = data.result;
															var rowStr = '';
															var headerStr = '<tr class="gft-header gft-single-header">';
															$
																	.each(
																			result,
																			function(
																					i) {
																				var resultObj = result[i];
																				rowStr += '<tr>';
																				for ( var key in resultObj) {
																					//print header once
																					if (headerFlag) {
																						headerStr += '<td class="gft2-1">'
																								+ key
																								+ '</td>';
																					}

																					var value = resultObj[key];
																					//alert(key+'--'+resultObj[key]);
																					rowStr += '<td class="gft-content gft-content2 tgt-even">'
																							+ value
																							+ '</td>';

																				}
																				if (headerFlag) {
																					headerStr += '</tr>';
																					headerFlag = false;
																				}

																				rowStr += '</tr>';
																			});
															$("#dataHead")
																	.html(
																			headerStr);
															$("#dataBody")
																	.html(
																			rowStr);
															//rowStr += "<tr id='"+i+"' class='lpid-details gft-content gft-content2 tgt-even'><td>"+i+"</td><td>"+rd+"</td></tr>";

														}
													});

										});
					});
</script>
</head>
<body>
	<table style="height: 40%; width: 100%; position: static;">
		<tr>
			<td style="height: 20%; width: 20%;">Enter Rest End Point URL:</td>
			<td style="height: 20%; width: 80%;"><input
				style="width: 100%; height: 100%;" type="text" id="endPoint"
				value="http://localhost:8080/sparksupporttool/query/" /></td>
		</tr>
		<tr>
			<td style="height: 100%; width: 20%;">Enter Query:</td>
			<td style="height: 100%; width: 80%;"><textarea
					style="width: 100%; height: 100%;" id="queryInput"></textarea></td>
		</tr>
		<tr>
			<td></td>
			<td><input id="runQuery" type="button" value="Run" /></td>

		</tr>
	</table>
	<table>

		<thead class="gft-header" id="dataHead">
		</thead>
		<tbody id="dataBody"></tbody>
	</table>
	<div id="errorMsg" style="color: red;"></div>
</body>
</html>