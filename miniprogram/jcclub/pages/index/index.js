//获取应用实例
const app = getApp()

Page({
  data: {
    swiperCurrent: 0,
    indicatorDots: true,
    autoplay: true,
    interval: 3000,
    duration: 800,
    circular: true,
    imgUrls: [],
    articles: [],
    title:""
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;

    wx.request({
      url: app.globalData.requestUri + '/carousel', 
      success: function (res) {
        that.setData({
          imgUrls:res.data.data
        })
        
      }
    }),
      wx.request({
      url: app.globalData.requestUri + '/article', 
        success: function (res) {
          console.log(res.data.data)
          that.setData({
            articles: res.data.data
          })
        }
      })
  },
  //轮播图的切换事件
  swiperChange: function (e) {
    this.setData({
      swiperCurrent: e.detail.current
    })
  },
  //点击指示点切换
  chuangEvent: function (e) {
    this.setData({
      swiperCurrent: e.currentTarget.id
    })
  },
  //点击图片触发事件
  swipclick: function (e) {
    console.log(this.data.swiperCurrent);
    wx.switchTab({
      url: this.data.links[this.data.swiperCurrent]
    })
  },
  imageClick:function (event){
    wx.navigateTo({
      url: '/pages/out/out?articleUrl=' + event.currentTarget.dataset.articleurl
    })
  }
})