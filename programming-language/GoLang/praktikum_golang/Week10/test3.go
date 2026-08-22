func terkecilTest(arr arrInt, n int) int {
	res := arr[0]
	for i := 1; i < n; i++ {
		if arr[i] < res {
			res = arr[i]
		}
	}
	return res
}
