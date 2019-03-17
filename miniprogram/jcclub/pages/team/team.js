//获取应用实例
const app = getApp()

// pages/team/team.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      teams:{},
      userInfo: {},
      hasUserInfo: false,
      getUserInfoFail: false,
      canIUse: wx.canIUse('button.open-type.getUserInfo'),
      hasUserInfoId: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;

      if (app.globalData.userInfo) {
          console.log(1)
          that.setData({
              userInfo: app.globalData.userInfo,
              hasUserInfo: true
          })
      } else if (that.data.canIUse) {
          console.log(2)
          // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
          // 所以此处加入 callback 以防止这种情况
          app.userInfoReadyCallback = res => {
              console.log(12)
              app.globalData.userInfo = res.userInfo
              this.setData({
                  userInfo: res.userInfo,
                  hasUserInfo: true
              })
          }
      } else {
          console.log(3)
          // 在没有 open-type=getUserInfo 版本的兼容处理
          wx.getUserInfo({
              success: res => {
                  app.globalData.userInfo = res.userInfo
                  this.setData({
                      userInfo: res.userInfo,
                      hasUserInfo: true
                  })
              },
              fail: res => {
                  console.log(4);
                  this.setData({
                      getUserInfoFail: true
                  })
              }
          })
      }
      console.log("123333333333312313")
      console.log(this.data.hasUserInfo)
      console.log(this.data.userInfo)
      if (this.data.hasUserInfo){
          console.log("12333333333333")
          this.saveUserInfo().then(() => {
              wx.request({
                  url: app.globalData.requestUri + '/teamLibrary?actionName=list&userInfoId=' + app.globalData.userId,
                  success: function (res) {
                      that.setData({
                          teams:res.data.data
                      })
                  }
              })
          });
      }
  },

    onShow: function() {
      this.login();
    },

    login: function () {
        console.log(111)
        var that = this
        // if (typeof success == "function") {
        //   console.log(6);
        //   console.log('success');
        //   this.data.getUserInfoSuccess = success
        // }
        wx.login({
            success: function (res) {
                var code = res.code;
                console.log(code);
                wx.getUserInfo({
                    success: function (res) {
                        console.log(7);
                        app.globalData.userInfo = res.userInfo
                        that.setData({
                            getUserInfoFail: false,
                            userInfo: res.userInfo,
                            hasUserInfo: true

                        })
                        //平台登录
                    },
                    fail: function (res) {
                        console.log(8);
                        console.log(res);
                        that.setData({
                            getUserInfoFail: true
                        })
                    }
                })
            }
        })
    },
    //跳转设置页面授权
    openSetting: function () {
        var that = this
        if (wx.openSetting) {
            wx.openSetting({
                success: function (res) {
                    console.log(9);
                    //尝试再次登录
                    that.login()
                }
            })
        } else {
            console.log(10);
            wx.showModal({
                title: '授权提示',
                content: '小程序需要您的微信授权才能使用哦~ 错过授权页面的处理方法：删除小程序->重新搜索进入->点击授权按钮'
            })
        }
    },
    getUserInfo: function (e) {
      var that = this;
        console.log(5);
        console.log(e)
        if (e.detail.userInfo) {
            app.globalData.userInfo = e.detail.userInfo
            that.setData({
                userInfo: e.detail.userInfo,
                hasUserInfo: true
            })
        } else {
            that.openSetting();
        }
        that.saveUserInfo().then(() => {
            wx.request({
                url: app.globalData.requestUri + '/teamLibrary?actionName=list&userInfoId=' + app.globalData.userId,
                success: function (res) {
                    that.setData({
                        teams:res.data.data
                    })
                }
            })
        });
    },
    saveUserInfo: function(){
        var that = this;
        console.log(JSON.stringify(this.data.userInfo))
        // 发送保存微信信息的请求
        return new Promise(function (resolve, reject) {
            wx.request({
                url: app.globalData.requestUri + '/personal?actionName=auth',
                data: that.data.userInfo,
                success: function (res) {
                    app.globalData.userId = res.data.data,
                        that.setData({
                            hasUserInfoId: true
                        })
                    resolve();
                }
            })
        });
    },

  changeData: function () {
    this.onLoad();//最好是只写需要刷新的区域的代码，onload也可，效率低，有点low
  },

  toPublish: function(){
    wx.navigateTo({
      url: '/pages/teamInfo/teamInfo'
    })
  },

  delivery: function (event) {
    console.log(event.currentTarget.dataset)
    var that = this;
    wx.request({
      url: app.globalData.requestUri + '/teamLibrary?actionName=delivery&userInfoId=' + app.globalData.userId + '&teamId=' + event.currentTarget.dataset.teamid,
      success: function (res) {
        if (res.data.resCode == '200') {
          if(res.data.data === 1){
              wx.showToast({
                  title: '请先前往个人中心/我的信息,完善资料才能投递',
                icon: 'none',
                duration: 2000,
                  mask: true
              })
          }else if(res.data.data === 2){
              wx.showToast({
                  title: '不能投递自己的团队',
                  icon: 'none',
                duration: 2000,
                  mask: true
              })
          }else if(res.data.data === 3){
              wx.showToast({
                  title: '您已经投递,无需重复投递',
                icon: 'none',
                duration: 2000,
                  mask: true
              })
          }else if(res.data.data === 4){
              wx.showToast({
                  title: '投递成功,请耐心等候团队负责人联系',
                  icon: 'succes',
                  duration: 2000,
                  mask: true
              })
            that.onLoad();
          }
        }
      }
    })
  },

})