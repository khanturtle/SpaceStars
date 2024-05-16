import LoginButton from './LoginButton'
import styles from './Navbar.module.css'

function Navbar() {
  return (
    <nav className={styles.nav}>
      <div className={styles.nav_logo}>
        <h1 className="hidden">SPACE STAR</h1>
        로고
      </div>

      <ul className={styles.nav_item}>
        <li>1</li>
        <li>2</li>
        <li>3</li>
      </ul>

      <div className="flex-1" />

      <div className={styles.nav_mode}>모드</div>

      <LoginButton styles={styles} />
    </nav>
  )
}

export default Navbar
