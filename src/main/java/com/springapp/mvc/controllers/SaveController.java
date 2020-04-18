package com.springapp.mvc.controllers;

import com.springapp.mvc.model_for_users.*;
import com.springapp.mvc.models2.Categories2;
import com.springapp.mvc.models2.Company2;
import com.springapp.mvc.models2.Success;
import com.springapp.mvc.models2.UndGroups2;
import com.springapp.mvc.service.MyServiceClass;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/")
public class SaveController {
	@Autowired
	MyServiceClass myServiceClass;
	@RequestMapping(value = "/save_company",method = RequestMethod.POST)
	public String save_company(
			@RequestParam("name") String name,
			@RequestParam("login") String login,
			@RequestParam("password") String password,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam("file") MultipartFile photo,HttpServletRequest httpServletRequest,ModelMap modelMap) {
		DealerAdmin dealerAdmin = (DealerAdmin) httpServletRequest.getSession().getAttribute("admin");
		try {
			dealerAdmin.getId();
			StringBuilder stringBuilder = new StringBuilder();
			if (photo != null && photo.getOriginalFilename() != "") {
				String file_name = null;
				MultipartFile file = photo;
				file_name = photo.getOriginalFilename();
				file_name = FilenameUtils.getExtension(file_name);
				try {
					int maxCount = myServiceClass.maxCompanies();maxCount++;
					byte[] bytes = file.getBytes();
					String rootPath = "C:"+File.separator+"path"+File.separator+"companies"+File.separator+maxCount+File.separator;
					File dir = new File(rootPath + File.separator);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					RandomString randomString =new RandomString(maxCount);
					String randomStr = randomString.nextString();
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + randomStr + "." + file_name);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stringBuilder.append(randomStr + "." + file_name);
					stream.close();
					FileInputStream fis = new FileInputStream(serverFile);
					BufferedImage bufferedImage = ImageIO.read(fis);
					BufferedImage resBufferedImage = createResizedCopy(bufferedImage, bufferedImage.getWidth()/6, bufferedImage.getHeight()/6, true);
					File file1 = new File(rootPath+File.separator+"small"+File.separator);
					if(!file1.exists()){
						file1.mkdirs();
					}
					File file2 = new File(file1.getAbsolutePath()+File.separator+stringBuilder);
					ImageIO.write(resBufferedImage,file_name,file2);
				} catch (Exception e) {
					return null;
				}


			} else if (photo == null || photo.getOriginalFilename() == "") {
				return null;
			}
			try {
				if (name != null && name.length() != 0 && !name.equals("")) {
					if (login != null && login.length() != 0 && !login.equals("")) {
						boolean result = myServiceClass.getLoginCompany(login);
						if(result){
							modelMap.addAttribute("remove", false);
							modelMap.addAttribute("login", "Такой логин существует выберите другую!");
							return "admin_add_company_result";
						}
						if (password != null && password.length() != 0 && !password.equals("")) {
							if (address != null && address.length() != 0 && !address.equals("")) {
								if (login != null && login.length() != 0 && !login.equals("")) {
									if (phone != null && phone.length() != 0 && !phone.equals(""))  {
										if (password != null && password.length() != 0 && !password.equals("")) {
											Company company = new Company();
											company.setName(name);
											company.setLogin(login);
											company.setAddress(address);
											company.setPassword(password);
											company.setPhone(phone);
											company.setPhoto(stringBuilder.toString());
											myServiceClass.save(company);
											modelMap.addAttribute("remove", false);
											modelMap.addAttribute("login", "");
											return "admin_add_company_result";
										}
									}
								}
							}
						}

					}
				}
			} catch (NullPointerException e) {}
		}catch (NullPointerException e){}
		return "sign_in_admin";
	}
	@ResponseBody
	@RequestMapping(value = "/save_shop",method = RequestMethod.POST)
	public Object save_shop(
			@RequestParam("name") String name,
			@RequestParam("login") String login,
			@RequestParam("password") String password,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam("latitude") String latitude,
			@RequestParam("longitude") String longitude,
			@RequestParam("groupsusId") String groupsusId,
			@RequestParam("file") MultipartFile photo) {
		Success success = new Success();
		try {
			StringBuilder stringBuilder = new StringBuilder();
			if (photo != null && photo.getOriginalFilename() != "") {
				String file_name = null;
				MultipartFile file = photo;
				file_name = photo.getOriginalFilename();
				file_name = FilenameUtils.getExtension(file_name);
				try {
					byte[] bytes = file.getBytes();
					String rootPath = "C:"+File.separator+"path"+File.separator+"shops"+File.separator;
					File dir = new File(rootPath + File.separator);
					if (!dir.exists()) {
						dir.mkdirs();
					}

					RandomString randomString = new RandomString();
					String randomStr = randomString.nextString();
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + randomStr + "." + file_name);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stringBuilder.append(randomStr + "." + file_name);
					stream.close();

					FileInputStream fis = new FileInputStream(serverFile);
					BufferedImage bufferedImage = ImageIO.read(fis);
					BufferedImage resBufferedImage = createResizedCopy(bufferedImage, bufferedImage.getWidth()/6, bufferedImage.getHeight()/6, true);
					File file1 = new File(rootPath+File.separator+"small"+File.separator);
					if(!file1.exists()){
						file1.mkdirs();
					}
					File file2 = new File(file1.getAbsolutePath()+File.separator+stringBuilder);
					ImageIO.write(resBufferedImage,file_name,file2);
				}catch (Exception e){e.printStackTrace();}


			} else if (photo == null || photo.getOriginalFilename() == "") {
				success.setResult(500);
				return success;
			}
			try {
				if (name != null && name.length() != 0 && !name.equals("")) {
					if (login != null && login.length() != 0 && !login.equals("")) {
						if (password != null && password.length() != 0 && !password.equals("")) {
							if (address != null && address.length() != 0 && !address.equals("")) {
								if (login != null && login.length() != 0 && !login.equals("")) {
									boolean result = myServiceClass.getLoginShop(login);
									if(result){
										success.setResult(222);
										return success;
									}
									if (phone != null && phone.length() != 0 && !phone.equals("")) {
										if (latitude != null && latitude.length() != 0 && !latitude.equals("")) {
											if (longitude != null && longitude.length() != 0 && !longitude.equals("")) {
												if (groupsusId != null && groupsusId.length() != 0 && !groupsusId.equals("")) {
													if (password != null && password.length() != 0 && !password.equals("")) {
														Shops shops = new Shops();
														shops.setName(name);
														shops.setLogin(login);
														shops.setAddress(address);
														shops.setPassword(password);
														shops.setPhone(phone);
														shops.setLatitude(latitude);
														shops.setLongitude(longitude);
														GroupsUs groupsUs = new GroupsUs();
														groupsUs.setId(Integer.valueOf(groupsusId));
														shops.setGroupsus(groupsUs);
														shops.setPhoto(stringBuilder.toString());
														myServiceClass.save(shops);
														success.setResult(200);
														return success;
													}
												}
											}
										}
									}
								}
							}
						}

					}
				}
			} catch (NullPointerException e) {}
		}catch (NullPointerException e){}
		success.setResult(500);
		return success;
	}
	@RequestMapping(value = "/save_courier",method = RequestMethod.POST)
	public String save_courier(
			@RequestParam("id_comp") String id_comp,
			@RequestParam("name") String name,
			@RequestParam("l_name") String l_name,
			@RequestParam("address") String address,
			@RequestParam("login") String login,
			@RequestParam("phone") String phone,
			@RequestParam("password") String password,
			@RequestParam("file") MultipartFile photo,ModelMap modelMap,HttpServletRequest httpServletRequest) {
		Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
		try {
			company2.getId();
		StringBuilder stringBuilder = new StringBuilder();
		if (photo != null && photo.getOriginalFilename() != "") {
			String file_name = null;
			MultipartFile file = photo;
			file_name = photo.getOriginalFilename();
			file_name = FilenameUtils.getExtension(file_name);
			try {
				byte[] bytes = file.getBytes();
				String rootPath = "C:"+File.separator+"path"+File.separator+"companies"+File.separator+id_comp+File.separator+"couriers"+File.separator;
				File dir = new File(rootPath + File.separator);
				if (!dir.exists())
					dir.mkdirs();
				RandomString randomString = new RandomString(Integer.valueOf(id_comp));
				String randomStr = randomString.nextString();
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + randomStr + "." + file_name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stringBuilder.append(randomStr + "." + file_name);
				stream.close();
				FileInputStream fis = new FileInputStream(serverFile);
				BufferedImage bufferedImage = ImageIO.read(fis);
				BufferedImage resBufferedImage = createResizedCopy(bufferedImage, bufferedImage.getWidth()/6, bufferedImage.getHeight()/6, true);
				File file1 = new File(rootPath+File.separator+"small"+File.separator);
				if(!file1.exists()){
					file1.mkdirs();
				}
				File file2 = new File(file1.getAbsolutePath()+File.separator+stringBuilder);
				ImageIO.write(resBufferedImage,file_name,file2);
			} catch (Exception e) {
				return "<h1><font size=\"35px\" style=\"color:#006064\";>Oshibka zagruzki failov</font></h1>";
			}


		} else if (photo == null || photo.getOriginalFilename() == "") {
			return "add_courier";
		}
		try {
			if (id_comp != null && id_comp.length() != 0 && !id_comp.equals("")) {
				if (name != null && name.length() != 0 && !name.equals("")) {
					if (l_name != null && l_name.length() != 0 && !l_name.equals("")) {
						if (address != null && address.length() != 0 && !address.equals("")) {
							if (login != null && login.length() != 0 && !login.equals("")) {
								boolean result = myServiceClass.getLoginCourier(login);
								if(result){
									modelMap.addAttribute("login", "Такой логин существует выберите другую!");
									modelMap.addAttribute("success", "");
									return "add_courier2";
								}
								if (phone != null && phone.length() != 0 && !phone.equals("")) {
									if (password != null && password.length() != 0 && !password.equals("")) {
										Courier courier = new Courier();
										Company company = new Company();
										company.setId(Integer.valueOf(id_comp));
										courier.setCompany(company);
										courier.setName(name);
										courier.setL_name(l_name);
										courier.setAddress(address);
										courier.setLogin(login);
										courier.setPhone(phone);
										courier.setPassword(password);
										courier.setPhoto(stringBuilder.toString());
										myServiceClass.save(courier);
										modelMap.addAttribute("login", "");
										modelMap.addAttribute("success", "success");
										return "add_courier2";
									}
								}
							}
						}
					}

				}
			}
		} catch (NullPointerException e) {}
	}catch (NullPointerException e){}
		return "sign_in";
	}









	@RequestMapping(value = "/save_product",method = RequestMethod.POST)
	public String save_product(
			@RequestParam("id_comp") String id_comp,
			@RequestParam("product_category") String product_category,
			@RequestParam("product_name") String product_name,
			@RequestParam("product_price") String product_price,
			@RequestParam("product_price_recommendation") String product_price_recommendation,
			@RequestParam("product_weight") String product_weight,
			@RequestParam("product_description") String product_description,
			@RequestParam(value = "product_isReturned", required = false) String product_isReturned,
			@RequestParam(value = "product_isDebt", required = false) String product_isDebt,
			@RequestParam("file") MultipartFile photo,ModelMap modelMap,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
	Company2 company2 = (Company2)httpSession.getAttribute("company");

	try {
		company2.getId();
		StringBuilder stringBuilder = new StringBuilder();
		if (photo != null && photo.getOriginalFilename() != "") {
			String file_name = null;
			MultipartFile file = photo;
			file_name = photo.getOriginalFilename();
			file_name = FilenameUtils.getExtension(file_name);
			try {
				byte[] bytes = file.getBytes();
				String rootPath = "C:"+File.separator+"path"+File.separator+"companies"+File.separator+id_comp+File.separator+"products"+File.separator;
				File dir = new File(rootPath + File.separator);
				if (!dir.exists()){
					dir.mkdirs();
				}
				RandomString randomString = new RandomString(Integer.valueOf(id_comp));
				String randomStr = randomString.nextString();
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + randomStr + "." + file_name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stringBuilder.append(randomStr + "." + file_name);
				stream.close();
				FileInputStream fis = new FileInputStream(serverFile);
				BufferedImage bufferedImage = ImageIO.read(fis);
				BufferedImage resBufferedImage = createResizedCopy(bufferedImage, bufferedImage.getWidth()/6, bufferedImage.getHeight()/6, true);
				File file1 = new File(rootPath+File.separator+"small"+File.separator);
				if(!file1.exists()){
					file1.mkdirs();
				}
				File file2 = new File(file1.getAbsolutePath()+File.separator+stringBuilder);
				ImageIO.write(resBufferedImage,file_name,file2);
			} catch (Exception e) {
				return "<h1><font size=\"35px\" style=\"color:#006064\";>Oshibka zagruzki failov</font></h1>";
			}


		} else if (photo == null || photo.getOriginalFilename() == "") {
			return "add_product2";
		}
		try {
			if (product_category != null && product_category.length() != 0 && !product_category.equals("")) {
				if (product_name != null && product_name.length() != 0 && !product_name.equals("")) {
					if (product_price != null && product_price.length() != 0 && !product_price.equals("")) {
						if (product_price_recommendation != null && product_price_recommendation.length() != 0 && !product_price_recommendation.equals("")) {
							if (product_weight != null && product_weight.length() != 0 && !product_weight.equals("")) {
								if (product_description != null && product_description.length() != 0 && !product_description.equals("")) {

									Products products = new Products();
									Company company = new Company();

									company.setId(company2.getId());

									products.setStatus(false);
									products.setCompany(company);
									products.setName(product_name);
									products.setPrice(product_price);
									products.setRec_price(product_price_recommendation);
									products.setWeight(product_weight);
									products.setDescription(product_description);

									if(product_isReturned!=null){
										products.setReturned(true);
									}else{
										products.setReturned(false);
									}
									if(product_isDebt!=null){
										products.setConsignation(true);
									}else{
										products.setConsignation(false);
									}


									products.setPhoto(stringBuilder.toString());
									myServiceClass.save(products, httpSession, product_category);
									modelMap.addAttribute("success", "success");
									modelMap.addAttribute("productDeleted", false);
									return "add_product2";

								}
							}
						}
					}

				}
			}
		} catch (NullPointerException e) {}
	}catch (NullPointerException e){

	}
	return "sign_in";
}

	@RequestMapping(value = "/save_category",method = RequestMethod.POST)
	public String save_category(@RequestParam("mainCategory") String mainCategory,HttpServletRequest httpServletRequest,ModelMap model) {
		Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
		HttpSession httpSession = httpServletRequest.getSession();
		try {
			company2.getId();
				Categories categories = new Categories();
				categories.setCategory(mainCategory);
				Company company = new Company();
				company.setId(company2.getId());
				categories.setCompany(company);

			myServiceClass.save(categories);
			List<Categories> categoriesList = myServiceClass.getAllCategories(company);
			Categories2 [] categories2s = new Categories2[categoriesList.size()];
			for(int i =0;i<categoriesList.size();i++){
				Categories2 categories2 = new Categories2();
				Categories categories1 = categoriesList.get(i);
				categories2.setId(categories1.getId());
				categories2.setCategory(categories1.getCategory());
				categories2s[i] = categories2;
			}
			httpSession.setAttribute("categories",categories2s);
			model.addAttribute("categoryDeleted",false);
			model.addAttribute("message", "add_category!");
			return "add_category2";
		}catch(NullPointerException e){
			return "sign_in";
		}
	}

	@RequestMapping(value = "/save_groups",method = RequestMethod.POST)
	public String save_groups(@RequestParam("groups") String groups,HttpServletRequest httpServletRequest,ModelMap model) {
		Company2 company2 = (Company2) httpServletRequest.getSession().getAttribute("company");
		try {
			company2.getId();
			Groups groups1 = new Groups();
			groups1.setName(groups);
			Company company = new Company();
			company.setId(company2.getId());
			groups1.setCompany(company);
			groups1.setPriceGroup("0");
			groups1.setPn(false);
			groups1.setVt(false);
			groups1.setSr(false);
			groups1.setCh(false);
			groups1.setPt(false);
			groups1.setSb(false);
			groups1.setVs(false);
			myServiceClass.save(groups1);
			model.addAttribute("message", "add_category!");
			model.addAttribute("groupDeleted",false);
			return "add_groups2";
		}catch(NullPointerException e){
			return "sign_in";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/save_undGroup",method = RequestMethod.POST)
	public Object save_undGroup(@RequestBody UndGroups2 undGroups2,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		Company2 company2 = (Company2)httpSession.getAttribute("company");
        UndGroups undGroups = new UndGroups();
		Shops shops = new Shops();
		Company company = new Company();
		Groups groups = new Groups();
		try {
			company.setId(company2.getId());
			shops.setId(Integer.valueOf(undGroups2.getShopId()));
			groups.setId(Integer.valueOf(undGroups2.getGrId()));
			undGroups.setShops(shops);
			undGroups.setCompany(company);
			undGroups.setGroups(groups);
			boolean res = myServiceClass.save(undGroups);
			Success success = new Success();
			if(res){
				success.setResult(200);
			}else{
				success.setResult(500);
			}
			return success;
		}catch (NullPointerException e){
			return "null";
		}

	}




















/*==============================================REST_API============================================*/


	@ResponseBody
	@RequestMapping(value = "/save_group_us",method = RequestMethod.POST)
	public Object save_GroupUs(@RequestBody GroupsUs groupsUs ) {
		Success success = new Success();
		try {
			myServiceClass.save(groupsUs);
			success.setResult(200);
			return success;
		}catch (Exception e){}
		success.setResult(500);
		return success;
	}


/*==============================================REST_API============================================*/











	BufferedImage createResizedCopy(Image originalImage,
									int scaledWidth, int scaledHeight,
									boolean preserveAlpha)
	{
		System.out.println("resizing...");
		int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
		Graphics2D g = scaledBI.createGraphics();
		if (preserveAlpha) {
			g.setComposite(AlphaComposite.Src);
		}
		g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
		g.dispose();
		return scaledBI;
	}

}