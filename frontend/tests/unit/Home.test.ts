import { mount } from '@vue/test-utils'
import Home from '../../src/views/Home.vue'

describe('Home.vue', () => {
  test('renders home page title', () => {
    const wrapper = mount(Home)
    expect(wrapper.find('h1').text()).toBe('家庭药箱管理平台')
  })

  test('renders dashboard section', () => {
    const wrapper = mount(Home)
    expect(wrapper.find('.dashboard').exists()).toBe(true)
  })

  test('renders quick stats section', () => {
    const wrapper = mount(Home)
    expect(wrapper.find('.quick-stats').exists()).toBe(true)
  })

  test('renders recent alerts section', () => {
    const wrapper = mount(Home)
    expect(wrapper.find('.recent-alerts').exists()).toBe(true)
  })
})
