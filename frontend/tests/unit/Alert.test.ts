import { mount } from '@vue/test-utils'
import Alert from '../../src/views/Alert.vue'

describe('Alert.vue', () => {
  test('renders alert management title', () => {
    const wrapper = mount(Alert)
    expect(wrapper.find('h1').text()).toBe('报警管理')
  })

  test('renders alert settings section', () => {
    const wrapper = mount(Alert)
    expect(wrapper.find('.alert-settings').exists()).toBe(true)
  })

  test('renders alert history section', () => {
    const wrapper = mount(Alert)
    expect(wrapper.find('.alert-history').exists()).toBe(true)
  })

  test('renders notification methods section', () => {
    const wrapper = mount(Alert)
    expect(wrapper.find('.notification-methods').exists()).toBe(true)
  })
})
