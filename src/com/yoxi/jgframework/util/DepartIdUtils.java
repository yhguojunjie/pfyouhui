package com.yoxi.jgframework.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 部门id工具类
 * 
 * @author liyh
 * 
 * @date 2014年4月29日 上午9:43:45
 */
public class DepartIdUtils {
	private static char[] letters = { 'a', 'b', 'c', 'd', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z' };

	/**
	 * 部门id 12345678 12 -> level=1;34 -> level=2 ;56->level=3;78->level=4
	 * 
	 * @param currentDepartId
	 * @param currentLevel
	 * @return
	 */
	public static String nextDepartId(String currentDepartId,
			Integer currentLevel) {
		if (StringUtils.isNotBlank(currentDepartId)
				&& StringUtils.length(currentDepartId) == 8) {
			String partOne = currentDepartId.substring(0, 2);
			String partTwo = currentDepartId.substring(2, 4);
			String partThree = currentDepartId.substring(4, 6);
			String partFour = currentDepartId.substring(6, 8);
			String departId = null;
			String nextValue = null;
			if (currentLevel == 1) {
				if (StringUtils.equals("z9", partOne)) {
					return "顶级部门id已经超过限制，请联系管理员";
				}
				nextValue = next(partOne);
				departId = nextValue + "000000";
			} else if (currentLevel == 2) {
				if (StringUtils.equals("z9", partTwo)) {
					return "二级部门id已经超过限制，请联系管理员";
				}
				departId = partOne + next(partTwo) + "0000";
			} else if (currentLevel == 3) {
				if (StringUtils.equals("z9", partThree)) {
					return "三级部门id已经超过限制，请联系管理员";
				}
				departId = partOne + partTwo + next(partThree) + "00";
			} else if (currentLevel == 4) {
				if (StringUtils.equals("z9", partFour)) {
					return "三级部门id已经超过限制，请联系管理员";
				}
				departId = partOne + partTwo + partThree + next(partFour);
			}
			return departId;
		} else {
			return null;
		}
	}

	/**
	 * 部分部门id进来，产生下一个数字 如：99产生a0;a0产生a1
	 * 
	 * @param partDepartId
	 * @return
	 */
	public static String next(String partDepartId) {
		if (StringUtils.isNotBlank(partDepartId)
				&& StringUtils.length(partDepartId) == 2) {
			if (NumberUtils.isNumber(partDepartId)) {// 是数字，0-99
				Integer part = Integer.valueOf(partDepartId);
				if (part < 99) {
					part++;
					if (part < 10) {
						return "0" + String.valueOf(part);
					}
					return String.valueOf(part);
				} else {
					return "a0";
				}
			} else {// 非数字的处理 十位0~z，个位0-9 a0 a1
				String firstChar = partDepartId.substring(0, 1);
				String lastChar = partDepartId.substring(1, 2);
				Integer geWei = Integer.valueOf(lastChar);
				if (geWei < 9) {
					return firstChar + (geWei + 1);
				} else {
					int index = ArrayUtils.indexOf(letters,
							firstChar.toCharArray()[0]);
					index++;
					if (index == 26) {
						return null;
					} else {
						return letters[index] + "0";
					}
				}
			}
		}
		return null;
	}
}
