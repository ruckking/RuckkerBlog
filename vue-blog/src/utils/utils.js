
// 加上async/await
export const CustomForeach = async (arr, callback) => {
  const length = arr.length
  const O = Object(arr)
  let k = 0
  while (k < length) {
    if (k in O) {
      const kValue = O[k]
      await callback(kValue, k, O)
    }
    k++
  }
}
