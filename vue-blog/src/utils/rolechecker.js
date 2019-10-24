
export function CheckAdminrole () {
  if (window.localStorage.getItem('role')) {
    var role = window.localStorage.getItem('role')
  } else {
    alert('尚未登录!')
    this.$router.push({ path: '/Login' })
  }
  if (role !== 'admin') {
    alert('你访问了你不该访问的页面！')
    this.$router.go(-1)
  }
}
export function CheckUserrole () {
  if (window.localStorage.getItem('role')) {
    var role = window.localStorage.getItem('role')
  } else {
    alert('尚未登录!')
    this.$router.push({ path: '/Login' })
  }
  if (role !== 'user') {
    alert('你访问了你不该访问的页面！')
    this.$router.go(-1)
  }
}
