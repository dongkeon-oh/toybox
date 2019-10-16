// 바이트 계산
function get_byte_calc(target) {
	var calc_byte = 0;
	for (var idx = 0; idx < target.length; idx++) {
		var note_char = escape(target.charAt(idx));

		if (note_char.length == 1)
			calc_byte++;
		else if (note_char.indexOf("%u") != -1)
			calc_byte += 2;
		else if (note_char.indexOf("%") != -1)
			calc_byte += note_char.length / 3;
	}

	return calc_byte;
}
